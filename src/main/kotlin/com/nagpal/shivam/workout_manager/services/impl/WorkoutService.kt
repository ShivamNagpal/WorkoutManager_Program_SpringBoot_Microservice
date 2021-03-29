package com.nagpal.shivam.workout_manager.services.impl

import com.nagpal.shivam.workout_manager.dtos.request.WorkoutRequestDto
import com.nagpal.shivam.workout_manager.dtos.response.DrillResponseDto
import com.nagpal.shivam.workout_manager.dtos.response.SectionResponseDto
import com.nagpal.shivam.workout_manager.dtos.response.WorkoutResponseDto
import com.nagpal.shivam.workout_manager.exceptions.ResponseException
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
    override fun saveWorkout(workoutRequestDto: WorkoutRequestDto): WorkoutResponseDto {
        var workout = try {
            Workout(workoutRequestDto)
        } catch (e: IllegalArgumentException) {
            throw ResponseException(
                HttpStatus.BAD_REQUEST,
                ErrorMessages.INVALID_WORKOUT_LEVEL(workoutRequestDto.level!!)
            )
        }
        workout = workoutRepository.save(workout)
        return WorkoutResponseDto(workout)
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
            val sectionResponseDtoMutableList = mutableListOf<SectionResponseDto>()
            workoutResponseDto.sections = sectionResponseDtoMutableList
            val sectionIds = sections.map { it.id!! }
            val sectionDrills = sectionDrillRepository.findBySectionIds(sectionIds)
            val sectionDrillsGroupedBySectionId = sectionDrills.groupBy { it.sectionId!! }

            val drillIds = sectionDrills.map { it.drillId!! }
            val drills = drillRepository.findAllById(drillIds)
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
}
