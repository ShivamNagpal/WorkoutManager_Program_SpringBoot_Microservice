package com.nagpal.shivam.workout.manager.dtos.request

import jakarta.validation.constraints.NotBlank

data class DrillRequestDto(
    @field:NotBlank
    val name: String? = null,
)
