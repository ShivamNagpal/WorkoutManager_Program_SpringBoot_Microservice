package com.nagpal.shivam.workout_manager.dtos.request

import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class SectionDrillRequestDto(
    @field:NotBlank
    val sectionId: String? = null,

    @field:NotBlank
    val drillId: String? = null,

    @field:NotNull
    @field:Min(value = 1)
    val length: Long? = null,

    @field:NotBlank
    val units: String? = null,
)
