package com.nagpal.shivam.workout_manager.dtos.request

import jakarta.validation.Valid
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class SectionDeepSaveRequestDto(
    @field:NotBlank
    val name: String?,

    @field:NotNull
    @field:Min(value = 1)
    val repetitions: Int?,
    val restingInfo: String?,

    @field:Valid
    val drills: List<DrillDeepSaveRequestDto>? = null,
)
