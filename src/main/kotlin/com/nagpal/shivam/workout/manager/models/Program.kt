package com.nagpal.shivam.workout.manager.models

import com.nagpal.shivam.workout.manager.enums.WorkoutLevel
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

@Entity
class Program : BaseModel() {
    var name: String? = null

    @Enumerated(EnumType.STRING)
    var level: WorkoutLevel? = null
    var description: String? = null
    var equipments: String? = null
    var designedFor: String? = null
}
