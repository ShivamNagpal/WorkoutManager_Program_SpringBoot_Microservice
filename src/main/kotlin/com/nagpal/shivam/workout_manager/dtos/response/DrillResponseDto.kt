package com.nagpal.shivam.workout_manager.dtos.response

import com.fasterxml.jackson.annotation.JsonInclude
import com.nagpal.shivam.workout_manager.models.Drill

data class DrillResponseDto(
    var uuid: String? = null,
    var name: String? = null,
    @field:JsonInclude(JsonInclude.Include.NON_NULL)
    var length: Long? = null,
    @field:JsonInclude(JsonInclude.Include.NON_NULL)
    var units: String? = null,
    @field:JsonInclude(JsonInclude.Include.NON_NULL)
    var order: Int? = null,
    var description: String? = null,
) {
    constructor(drill: Drill) : this(
        drill.uuid.toString(),
        drill.name,
    )
}
