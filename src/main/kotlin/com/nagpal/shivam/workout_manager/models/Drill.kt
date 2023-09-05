package com.nagpal.shivam.workout_manager.models

import jakarta.persistence.Entity

@Entity
class Drill : BaseModel() {
    var name: String? = null
}
