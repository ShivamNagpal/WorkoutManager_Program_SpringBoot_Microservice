package com.nagpal.shivam.workout_manager.dtos.request

import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank

data class WorkoutRequestDto(
    @field:NotBlank
    val name: String?,

    @field:NotBlank
    val level: String?,

    val description: String?,
    val equipments: String?,
    @field:Valid
    val sections: List<SectionDeepSaveRequestDto>? = null,
)
