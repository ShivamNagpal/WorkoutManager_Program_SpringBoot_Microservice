package com.nagpal.shivam.workout_manager.services.impl

import com.nagpal.shivam.workout_manager.dtos.request.WorkoutRequestDto
import com.nagpal.shivam.workout_manager.dtos.response.WorkoutResponseDto
import com.nagpal.shivam.workout_manager.models.Workout
import com.nagpal.shivam.workout_manager.repositories.WorkoutRepository
import com.nagpal.shivam.workout_manager.services.IWorkoutService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class WorkoutService @Autowired constructor(
    private val workoutRepository: WorkoutRepository
) : IWorkoutService {
    override fun saveWorkout(workoutRequestDto: WorkoutRequestDto): WorkoutResponseDto {
        var workout = Workout(workoutRequestDto)
        workout = workoutRepository.save(workout)
        return WorkoutResponseDto(workout)
    }
}
