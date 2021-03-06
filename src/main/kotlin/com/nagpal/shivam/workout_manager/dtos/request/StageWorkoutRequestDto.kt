package com.nagpal.shivam.workout_manager.dtos.request

import javax.validation.constraints.NotNull

data class StageWorkoutRequestDto(
    @field:NotNull
    val stageId: Long? = null,

    @field:NotNull
    val workoutId: Long? = null,
)
