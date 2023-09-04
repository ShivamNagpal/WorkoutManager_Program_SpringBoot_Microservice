package com.nagpal.shivam.workout_manager.models

import com.nagpal.shivam.workout_manager.enums.WorkoutLevel
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

@Entity
class Workout : BaseModel() {
    var name: String? = null

    @Enumerated(EnumType.STRING)
    var level: WorkoutLevel? = null
    var description: String? = null
    var equipments: String? = null
}
