package com.nagpal.shivam.workout_manager.dtos.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class StageRequestDto(
    @field:NotNull
    val programId: Long?,
    @field:NotBlank
    val name: String?,
    val description: String?,
)
