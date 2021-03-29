package com.nagpal.shivam.workout_manager.services

import com.nagpal.shivam.workout_manager.dtos.request.WorkoutRequestDto
import com.nagpal.shivam.workout_manager.dtos.response.WorkoutResponseDto
import org.springframework.transaction.annotation.Transactional

@Transactional
interface IWorkoutService {
    fun saveWorkout(workoutRequestDto: WorkoutRequestDto): WorkoutResponseDto
    fun getWorkouts(page: Int, size: Int): List<WorkoutResponseDto>
    fun getWorkoutByUuid(uuidString: String, deepFetch: Boolean): WorkoutResponseDto
}
