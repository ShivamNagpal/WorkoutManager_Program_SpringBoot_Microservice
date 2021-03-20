package com.nagpal.shivam.workout_manager.services.impl

import com.nagpal.shivam.workout_manager.dtos.request.WorkoutRequestDto
import com.nagpal.shivam.workout_manager.dtos.response.WorkoutResponseDto
import com.nagpal.shivam.workout_manager.exceptions.ResponseException
import com.nagpal.shivam.workout_manager.models.Workout
import com.nagpal.shivam.workout_manager.repositories.WorkoutRepository
import com.nagpal.shivam.workout_manager.services.IWorkoutService
import com.nagpal.shivam.workout_manager.utils.ErrorMessages
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class WorkoutService @Autowired constructor(
    private val workoutRepository: WorkoutRepository
) : IWorkoutService {
    override fun saveWorkout(workoutRequestDto: WorkoutRequestDto): WorkoutResponseDto {
        var workout = try {
            Workout(workoutRequestDto)
        } catch (e: IllegalArgumentException) {
            throw ResponseException(
                HttpStatus.BAD_REQUEST,
                ErrorMessages.INVALID_WORKOUT_LEVEL(workoutRequestDto.level)
            )
        }
        workout = workoutRepository.save(workout)
        return WorkoutResponseDto(workout)
    }
}
