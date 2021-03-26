package com.nagpal.shivam.workout_manager.dtos.request

import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class SectionRequestDto(
    @field:NotBlank
    val workoutId: String?,

    @field:NotBlank
    val name: String?,

    @field:NotNull
    @field:Min(value = 1)
    val repetitions: Int?,
    val restingInfo: String?
)
