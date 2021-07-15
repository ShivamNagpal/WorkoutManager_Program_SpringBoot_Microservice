package com.nagpal.shivam.workout_manager.dtos.transformers

import com.nagpal.shivam.workout_manager.dtos.request.StageWorkoutRequestDto
import com.nagpal.shivam.workout_manager.dtos.response.StageWorkoutResponseDto
import com.nagpal.shivam.workout_manager.models.Stage
import com.nagpal.shivam.workout_manager.models.StageWorkout
import com.nagpal.shivam.workout_manager.models.Workout
import org.springframework.stereotype.Component
import java.util.*

@Component
class StageWorkoutTransformer {
    fun convertStageWorkoutRequestDtoToStageWorkout(
        stageWorkoutRequestDto: StageWorkoutRequestDto,
        stage: Stage,
        workout: Workout
    ): StageWorkout {
        val stageWorkout = StageWorkout()
        return stageWorkout.apply {
            this.stage = stage
            this.workout = workout
            stageId = stage.id
            workoutId = workout.id
        }
    }

    fun convertStageWorkoutToStageWorkoutResponseDto(
        stageWorkout: StageWorkout,
        stageUuid: UUID?,
        workoutUuid: UUID?
    ): StageWorkoutResponseDto {
        return StageWorkoutResponseDto(
            stageWorkout.uuid?.toString(),
            stageUuid?.toString(),
            workoutUuid?.toString(),
            stageWorkout.order
        )
    }
}
