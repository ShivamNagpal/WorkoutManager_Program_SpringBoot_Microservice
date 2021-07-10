package com.nagpal.shivam.workout_manager.dtos.request

import javax.validation.constraints.NotBlank

data class StageRequestDto(
    @field:NotBlank
    val programId: String?,
    @field:NotBlank
    val name: String?,
    val description: String?,
)
