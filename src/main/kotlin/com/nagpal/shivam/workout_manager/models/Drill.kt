package com.nagpal.shivam.workout_manager.models

import javax.persistence.Entity

@Entity
class Drill : BaseModel() {
    val name: String? = null
    val description: String? = null
}