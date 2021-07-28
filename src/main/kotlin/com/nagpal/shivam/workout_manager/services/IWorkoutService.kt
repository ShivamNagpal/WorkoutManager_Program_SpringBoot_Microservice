package com.nagpal.shivam.workout_manager.services

import com.nagpal.shivam.workout_manager.dtos.request.ReorderRequestDto
import com.nagpal.shivam.workout_manager.dtos.request.WorkoutRequestDto
import com.nagpal.shivam.workout_manager.dtos.response.WorkoutResponseDto
import org.springframework.transaction.annotation.Transactional

@Transactional
interface IWorkoutService {
    fun saveWorkout(workoutRequestDto: WorkoutRequestDto, deepSave: Boolean): WorkoutResponseDto
    fun getWorkouts(page: Int, pageSize: Int): List<WorkoutResponseDto>
    fun getWorkoutById(id: Long, deepFetch: Boolean): WorkoutResponseDto
    fun getWorkoutsInStage(stageId: Long): List<WorkoutResponseDto>
    fun reorderWorkouts(stageId: Long, reorderRequestDto: ReorderRequestDto): List<WorkoutResponseDto>
}
