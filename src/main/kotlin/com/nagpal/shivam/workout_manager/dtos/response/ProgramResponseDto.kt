package com.nagpal.shivam.workout_manager.dtos.response

data class ProgramResponseDto(
    val id: Long?,
    val name: String?,
    val level: String?,
    val description: String?,
    val equipments: String?,
    val designedFor: String?,
)
