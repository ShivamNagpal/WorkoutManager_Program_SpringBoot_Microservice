package com.nagpal.shivam.workout_manager.dtos.request

import javax.validation.constraints.NotBlank

data class DrillRequestDto(
    @field:NotBlank
    val name: String? = null,
    val description: String? = null
)
