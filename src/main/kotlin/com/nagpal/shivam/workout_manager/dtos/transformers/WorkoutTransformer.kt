package com.nagpal.shivam.workout_manager.dtos.transformers

import com.nagpal.shivam.workout_manager.dtos.request.WorkoutRequestDto
import com.nagpal.shivam.workout_manager.dtos.response.WorkoutResponseDto
import com.nagpal.shivam.workout_manager.enums.WorkoutLevel
import com.nagpal.shivam.workout_manager.models.Workout
import org.springframework.stereotype.Component
import java.util.*

@Component
class WorkoutTransformer {

    fun convertWorkoutRequestDtoToWorkout(workoutRequestDto: WorkoutRequestDto): Workout {
        val workout = Workout()
        return workout.apply {
            name = workoutRequestDto.name
            level = WorkoutLevel.valueOf(workoutRequestDto.level!!.uppercase(Locale.getDefault()))
            description = workoutRequestDto.description
            equipments = workoutRequestDto.equipments
        }
    }

    fun convertWorkoutToWorkoutResponseDto(workout: Workout): WorkoutResponseDto {
        return WorkoutResponseDto(
            workout.id,
            workout.name,
            workout.level.toString(),
            workout.description,
            workout.equipments
        )
    }
}
