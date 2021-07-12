package com.nagpal.shivam.workout_manager.dtos.request

import javax.validation.constraints.NotBlank

data class StageWorkoutRequestDto(
    @field:NotBlank
    val stageId: String? = null,

    @field:NotBlank
    val workoutId: String? = null,
)
