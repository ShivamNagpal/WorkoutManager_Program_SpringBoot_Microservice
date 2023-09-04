package com.nagpal.shivam.workout_manager.dtos.request

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class SectionDrillRequestDto(
    @field:NotNull
    val sectionId: Long? = null,

    @field:NotNull
    val drillId: Long? = null,

    @field:NotNull
    @field:Min(value = 1)
    val length: Long? = null,

    @field:NotBlank
    val units: String? = null,

    val description: String? = null,
)
