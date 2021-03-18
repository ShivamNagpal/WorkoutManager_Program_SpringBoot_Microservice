package com.nagpal.shivam.workout_manager.models

import com.nagpal.shivam.workout_manager.enums.WorkoutLevel
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
class Workout : BaseModel() {
    val name: String? = null

    @Enumerated(EnumType.STRING)
    val level: WorkoutLevel? = null
    val description: String? = null
    val equipments: String? = null
}
