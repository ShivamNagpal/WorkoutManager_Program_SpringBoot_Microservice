package com.nagpal.shivam.workout.manager.dtos.request

import jakarta.validation.constraints.NotEmpty

data class ReorderRequestDto(
    @field:NotEmpty
    val items: List<Long>?,
)
