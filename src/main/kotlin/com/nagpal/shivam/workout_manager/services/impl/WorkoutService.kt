package com.nagpal.shivam.workout_manager.services.impl

import com.nagpal.shivam.workout_manager.dtos.request.WorkoutRequestDto
import com.nagpal.shivam.workout_manager.dtos.response.DrillResponseDto
import com.nagpal.shivam.workout_manager.dtos.response.SectionResponseDto
import com.nagpal.shivam.workout_manager.dtos.response.WorkoutResponseDto
import com.nagpal.shivam.workout_manager.exceptions.ResponseException
import com.nagpal.shivam.workout_manager.models.Drill
import com.nagpal.shivam.workout_manager.models.Section
import com.nagpal.shivam.workout_manager.models.SectionDrill
import com.nagpal.shivam.workout_manager.models.Workout
import com.nagpal.shivam.workout_manager.repositories.DrillRepository
import com.nagpal.shivam.workout_manager.repositories.SectionDrillRepository
import com.nagpal.shivam.workout_manager.repositories.SectionRepository
import com.nagpal.shivam.workout_manager.repositories.WorkoutRepository
import com.nagpal.shivam.workout_manager.services.IWorkoutService
import com.nagpal.shivam.workout_manager.utils.ErrorMessages
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.*

@Service
class WorkoutService @Autowired constructor(
    private val workoutRepository: WorkoutRepository,
    private val sectionRepository: SectionRepository,
    private val sectionDrillRepository: SectionDrillRepository,
    private val drillRepository: DrillRepository,
) : IWorkoutService {
    override fun saveWorkout(workoutRequestDto: WorkoutRequestDto, deepSave: Boolean): WorkoutResponseDto {
        var workout = try {
            Workout(workoutRequestDto)
        } catch (e: IllegalArgumentException) {
            throw ResponseException(
                HttpStatus.BAD_REQUEST,
                ErrorMessages.invalidWorkoutLevel(workoutRequestDto.level!!)
            )
        }
        workout = workoutRepository.save(workout)
        val workoutResponseDto = WorkoutResponseDto(workout)
        if (deepSave) {
            deepSave(workoutRequestDto, workout, workoutResponseDto)
        }
        return workoutResponseDto
    }

    fun deepSave(workoutRequestDto: WorkoutRequestDto, workout: Workout, workoutResponseDto: WorkoutResponseDto) {
        val sectionDeepSaveRequestDtos = workoutRequestDto.sections
        val sectionList = mutableListOf<Section>()
        val drillMapByName = mutableMapOf<String, Drill>()
        val sectionDrillList = mutableListOf<SectionDrill>()
        if (sectionDeepSaveRequestDtos != null) {
            for ((sectionIndex, sectionDeepSaveRequestDto) in sectionDeepSaveRequestDtos.withIndex()) {
                val section = Section(sectionDeepSaveRequestDto, workout)
                section.order = sectionIndex + 1
                sectionList.add(section)
                val drillDeepSaveRequestDtos = sectionDeepSaveRequestDto.drills
                if (drillDeepSaveRequestDtos != null) {
                    for ((drillIndex, drillDeepSaveRequestDto) in drillDeepSaveRequestDtos.withIndex()) {
                        val drill = Drill(drillDeepSaveRequestDto)
                        drillMapByName[drill.name!!] = drill
                        val sectionDrill = try {
                            SectionDrill(drillDeepSaveRequestDto, section, drill)
                        } catch (e: IllegalArgumentException) {
                            throw ResponseException(
                                HttpStatus.BAD_REQUEST,
                                ErrorMessages.invalidDrillLengthUnit(drillDeepSaveRequestDto.units!!)
                            )
                        }
                        sectionDrill.order = drillIndex + 1
                        sectionDrillList.add(sectionDrill)
                    }
                }
            }
        }
        val existingDrills = drillRepository.findByNames(drillMapByName.keys)
        existingDrills.forEach {
            drillMapByName[it.name!!] = it
        }
        val drillList = drillMapByName.values
        sectionRepository.saveAll(sectionList)
        drillRepository.saveAll(drillList)
        sectionDrillList.forEach{
            it.drill = drillMapByName[it.drill!!.name]
        }
        sectionDrillRepository.saveAll(sectionDrillList)
        sectionDrillList.forEach{
            it.copyForeignKeyIdsToTheFields()
        }
        deepFetch(workoutResponseDto, sectionList, sectionDrillList, drillList)
    }

    override fun getWorkouts(page: Int, size: Int): List<WorkoutResponseDto> {
        val pageRequest = PageRequest.of(page, size)
        val workoutPage = workoutRepository.findAll(pageRequest)
        return workoutPage.content.map { WorkoutResponseDto(it) }
    }

    override fun getWorkoutByUuid(uuidString: String, deepFetch: Boolean): WorkoutResponseDto {
        val workoutUuid = UUID.fromString(uuidString)
        val workout = workoutRepository.findByUuid(workoutUuid)
            .orElseThrow {
                return@orElseThrow ResponseException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessages.WORKOUT_UUID_DOES_NOT_EXISTS
                )
            }

        val workoutResponseDto = WorkoutResponseDto(workout)
        if (deepFetch) {
            deepFetch(workout, workoutResponseDto)
        }
        return workoutResponseDto
    }

    fun deepFetch(workout: Workout, workoutResponseDto: WorkoutResponseDto) {
        val sections = sectionRepository.findByWorkoutId(workout.id!!)
        if (sections.isNotEmpty()) {
            val sectionIds = sections.map { it.id!! }
            val sectionDrills = sectionDrillRepository.findBySectionIds(sectionIds)
            if (sectionDrills.isNotEmpty()) {
                val drillIds = sectionDrills.map { it.drillId!! }
                val drills = drillRepository.findAllById(drillIds)
                deepFetch(workoutResponseDto, sections, sectionDrills, drills)
            }
        }
    }

    fun deepFetch(
        workoutResponseDto: WorkoutResponseDto,
        sections: Iterable<Section>,
        sectionDrills: Iterable<SectionDrill>,
        drills: Iterable<Drill>
    ) {
        val sectionResponseDtoMutableList = mutableListOf<SectionResponseDto>()
        workoutResponseDto.sections = sectionResponseDtoMutableList

        val sectionDrillsGroupedBySectionId = sectionDrills.groupBy { it.sectionId!! }
        val drillsMappedById = drills.map { it.id!! to it }.toMap()
        for (section in sections) {
            val sectionResponseDto = SectionResponseDto(section, null)
            sectionResponseDtoMutableList.add(sectionResponseDto)
            val sectionDrillsForCurrentSection = sectionDrillsGroupedBySectionId[section.id]
            if (sectionDrillsForCurrentSection != null && sectionDrillsForCurrentSection.isNotEmpty()) {
                val drillResponseDtoMutableList = mutableListOf<DrillResponseDto>()
                sectionResponseDto.drills = drillResponseDtoMutableList
                for (sectionDrill in sectionDrillsForCurrentSection) {
                    val drill = drillsMappedById[sectionDrill.drillId!!]
                    if (drill != null) {
                        val drillResponseDto = DrillResponseDto(drill)
                        drillResponseDtoMutableList.add(drillResponseDto)
                        drillResponseDto.uuid = sectionDrill.uuid?.toString()
                        drillResponseDto.length = sectionDrill.length
                        drillResponseDto.units = sectionDrill.units!!.toString()
                        drillResponseDto.order = sectionDrill.order
                        drillResponseDto.description = sectionDrill.description
                    }
                }
            }
        }
    }
}
