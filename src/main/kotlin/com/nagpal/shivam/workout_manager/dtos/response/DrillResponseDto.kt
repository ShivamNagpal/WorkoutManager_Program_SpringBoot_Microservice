package com.nagpal.shivam.workout_manager.dtos.response

import com.nagpal.shivam.workout_manager.models.Drill

data class DrillResponseDto(
    val uuid: String? = null,
    var name: String? = null,
    var description: String? = null
) {
    constructor(drill: Drill) : this(
        drill.uuid.toString(),
        drill.name,
        drill.description
    )
}
