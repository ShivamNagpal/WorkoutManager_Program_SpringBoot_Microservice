package com.nagpal.shivam.workout_manager.models

import com.nagpal.shivam.workout_manager.enums.WorkoutLevel
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
class Program : BaseModel() {
    var name: String? = null

    @Enumerated(EnumType.STRING)
    var level: WorkoutLevel? = null
    var description: String? = null
    var equipments: String? = null
    var designedFor: String? = null
}
