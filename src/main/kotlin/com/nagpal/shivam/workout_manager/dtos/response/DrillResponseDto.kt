package com.nagpal.shivam.workout_manager.dtos.response

import com.fasterxml.jackson.annotation.JsonInclude

data class DrillResponseDto(
    var id: Long? = null,
    var name: String? = null,
    @field:JsonInclude(JsonInclude.Include.NON_NULL)
    var length: Long? = null,
    @field:JsonInclude(JsonInclude.Include.NON_NULL)
    var units: String? = null,
    @field:JsonInclude(JsonInclude.Include.NON_NULL)
    var order: Int? = null,
    var description: String? = null,
)
