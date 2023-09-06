package com.nagpal.shivam.workout.manager.dtos.request

import jakarta.validation.constraints.NotNull

data class StageWorkoutRequestDto(
    @field:NotNull
    val stageId: Long? = null,

    @field:NotNull
    val workoutId: Long? = null,
)
