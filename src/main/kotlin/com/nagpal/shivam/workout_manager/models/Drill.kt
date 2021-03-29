package com.nagpal.shivam.workout_manager.models

import com.nagpal.shivam.workout_manager.dtos.request.DrillRequestDto
import javax.persistence.Entity

@Entity
class Drill() : BaseModel() {
    var name: String? = null

    constructor(drillRequestDto: DrillRequestDto) : this() {
        this.name = drillRequestDto.name
    }
}
