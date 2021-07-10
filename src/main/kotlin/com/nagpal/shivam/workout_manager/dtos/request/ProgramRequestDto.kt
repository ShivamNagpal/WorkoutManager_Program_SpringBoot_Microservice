package com.nagpal.shivam.workout_manager.dtos.request

import javax.validation.constraints.NotBlank

data class ProgramRequestDto(
    @field:NotBlank
    val name: String?,
    @field:NotBlank
    val level: String?,
    val description: String?,
    val equipments: String?,
    val designedFor: String?,
)
