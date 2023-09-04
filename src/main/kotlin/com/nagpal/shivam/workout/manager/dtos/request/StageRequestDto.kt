package com.nagpal.shivam.workout.manager.dtos.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class StageRequestDto(
    @field:NotNull
    val programId: Long?,
    @field:NotBlank
    val name: String?,
    val description: String?,
)
