package com.nagpal.shivam.workout_manager.models

import javax.persistence.Column
import javax.persistence.MappedSuperclass

@MappedSuperclass
class OrderedBaseModel : BaseModel() {
    @Column(name = "\"order\"")
    var order: Int? = null
}
