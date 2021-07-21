package com.nagpal.shivam.workout_manager.services.impl

import com.nagpal.shivam.workout_manager.dtos.request.WorkoutRequestDto
import com.nagpal.shivam.workout_manager.dtos.response.DrillResponseDto
import com.nagpal.shivam.workout_manager.dtos.response.SectionResponseDto
import com.nagpal.shivam.workout_manager.dtos.response.WorkoutResponseDto
import com.nagpal.shivam.workout_manager.dtos.transformers.DrillTransformer
import com.nagpal.shivam.workout_manager.dtos.transformers.SectionDrillTransformer
import com.nagpal.shivam.workout_manager.dtos.transformers.SectionTransformer
import com.nagpal.shivam.workout_manager.dtos.transformers.WorkoutTransformer
import com.nagpal.shivam.workout_manager.exceptions.ResponseException
import com.nagpal.shivam.workout_manager.models.Drill
import com.nagpal.shivam.workout_manager.models.Section
import com.nagpal.shivam.workout_manager.models.SectionDrill
import com.nagpal.shivam.workout_manager.models.Workout
import com.nagpal.shivam.workout_manager.repositories.*
import com.nagpal.shivam.workout_manager.services.IWorkoutService
import com.nagpal.shivam.workout_manager.utils.ErrorMessages
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class WorkoutService @Autowired constructor(
    private val workoutRepository: WorkoutRepository,
    private val sectionRepository: SectionRepository,
    private val sectionDrillRepository: SectionDrillRepository,
    private val drillRepository: DrillRepository,
    private val stageWorkoutRepository: StageWorkoutRepository,
    private val drillTransformer: DrillTransformer,
    private val workoutTransformer: WorkoutTransformer,
    private val sectionTransformer: SectionTransformer,
    private val sectionDrillTransformer: SectionDrillTransformer,
) : IWorkoutService {
    override fun saveWorkout(workoutRequestDto: WorkoutRequestDto, deepSave: Boolean): WorkoutResponseDto {
        var workout = try {
            workoutTransformer.convertWorkoutRequestDtoToWorkout(workoutRequestDto)
        } catch (e: IllegalArgumentException) {
            throw ResponseException(
                HttpStatus.BAD_REQUEST,
                ErrorMessages.invalidWorkoutLevel(workoutRequestDto.level!!)
            )
        }
        workout = workoutRepository.save(workout)
        val workoutResponseDto = workoutTransformer.convertWorkoutToWorkoutResponseDto(workout)
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
                val section =
                    sectionTransformer.convertSectionDeepSaveRequestDtoToSection(sectionDeepSaveRequestDto, workout)
                section.order = sectionIndex + 1
                sectionList.add(section)
                val drillDeepSaveRequestDtos = sectionDeepSaveRequestDto.drills
                if (drillDeepSaveRequestDtos != null) {
                    for ((drillIndex, drillDeepSaveRequestDto) in drillDeepSaveRequestDtos.withIndex()) {
                        val drill = drillTransformer.convertDrillDeepSaveRequestDtoToDrill(drillDeepSaveRequestDto)
                        drillMapByName[drill.name!!] = drill
                        val sectionDrill = try {
                            sectionDrillTransformer.convertDrillDeepSaveRequestDtoToSectionDrill(
                                drillDeepSaveRequestDto,
                                section,
                                drill
                            )
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
        sectionDrillList.forEach {
            it.drill = drillMapByName[it.drill!!.name]
        }
        sectionDrillRepository.saveAll(sectionDrillList)
        sectionDrillList.forEach {
            it.copyForeignKeyIdsToTheFields()
        }
        deepFetch(workoutResponseDto, sectionList, sectionDrillList, drillList)
    }

    override fun getWorkouts(page: Int, pageSize: Int): List<WorkoutResponseDto> {
        val pageRequest = PageRequest.of(page, pageSize)
        val workoutPage = workoutRepository.findAll(pageRequest)
        return workoutPage.content.map { workoutTransformer.convertWorkoutToWorkoutResponseDto(it) }
    }

    override fun getWorkoutById(id: Long, deepFetch: Boolean): WorkoutResponseDto {
        val workout = workoutRepository.findByIdAndDeleted(id)
            .orElseThrow {
                return@orElseThrow ResponseException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessages.WORKOUT_UUID_DOES_NOT_EXISTS
                )
            }

        val workoutResponseDto = workoutTransformer.convertWorkoutToWorkoutResponseDto(workout)
        if (deepFetch) {
            deepFetch(workout, workoutResponseDto)
        }
        return workoutResponseDto
    }

    override fun getWorkoutsInStage(stageId: Long): List<WorkoutResponseDto> {
        val stageWorkouts = stageWorkoutRepository.findAllByStageIdAndDeleted(stageId)
        val workoutIds = stageWorkouts.map { it.workoutId!! }
        val workouts = workoutRepository.findAllByIdIn(workoutIds)
        return workouts.map { workoutTransformer.convertWorkoutToWorkoutResponseDto(it) }
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
        val drillsMappedById = drills.associateBy { it.id!! }
        for (section in sections) {
            val sectionResponseDto = sectionTransformer.convertSectionToSectionResponseDto(section)
            sectionResponseDtoMutableList.add(sectionResponseDto)
            val sectionDrillsForCurrentSection = sectionDrillsGroupedBySectionId[section.id]
            if (sectionDrillsForCurrentSection != null && sectionDrillsForCurrentSection.isNotEmpty()) {
                val drillResponseDtoMutableList = mutableListOf<DrillResponseDto>()
                sectionResponseDto.drills = drillResponseDtoMutableList
                for (sectionDrill in sectionDrillsForCurrentSection) {
                    val drill = drillsMappedById[sectionDrill.drillId!!]
                    if (drill != null) {
                        val drillResponseDto = drillTransformer.convertDrillToDrillResponseDto(drill)
                        drillResponseDtoMutableList.add(drillResponseDto)
                        drillResponseDto.id = sectionDrill.id
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
