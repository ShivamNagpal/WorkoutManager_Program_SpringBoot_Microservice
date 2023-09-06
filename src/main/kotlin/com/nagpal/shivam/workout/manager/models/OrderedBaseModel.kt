package com.nagpal.shivam.workout.manager.models

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass

@MappedSuperclass
class OrderedBaseModel : BaseModel() {
    @Column(name = "\"order\"")
    var order: Int? = null
}
