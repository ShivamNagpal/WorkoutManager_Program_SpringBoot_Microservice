package com.nagpal.shivam.workout_manager.dtos.request

import javax.validation.constraints.NotEmpty

data class ReorderRequestDto(
    @field:NotEmpty
    val items: List<Long>?
)
