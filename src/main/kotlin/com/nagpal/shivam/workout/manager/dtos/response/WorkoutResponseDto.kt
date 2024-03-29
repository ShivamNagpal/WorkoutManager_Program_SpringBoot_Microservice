package com.nagpal.shivam.workout.manager.dtos.response

import com.fasterxml.jackson.annotation.JsonInclude

data class WorkoutResponseDto(
    val id: Long?,
    val name: String?,
    val level: String?,
    val description: String?,
    val equipments: String?,
    @field:JsonInclude(JsonInclude.Include.NON_NULL)
    var sections: List<SectionResponseDto>? = null,
)
