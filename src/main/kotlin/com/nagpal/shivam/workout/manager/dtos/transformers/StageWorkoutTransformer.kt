package com.nagpal.shivam.workout.manager.dtos.transformers

import com.nagpal.shivam.workout.manager.dtos.request.StageWorkoutRequestDto
import com.nagpal.shivam.workout.manager.dtos.response.StageWorkoutResponseDto
import com.nagpal.shivam.workout.manager.models.Stage
import com.nagpal.shivam.workout.manager.models.StageWorkout
import com.nagpal.shivam.workout.manager.models.Workout
import org.springframework.stereotype.Component

@Component
class StageWorkoutTransformer {
    fun convertStageWorkoutRequestDtoToStageWorkout(
        stageWorkoutRequestDto: StageWorkoutRequestDto,
        stage: Stage,
        workout: Workout,
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
    ): StageWorkoutResponseDto {
        return StageWorkoutResponseDto(
            stageWorkout.id,
            stageWorkout.stageId,
            stageWorkout.workoutId,
            stageWorkout.order,
        )
    }
}
