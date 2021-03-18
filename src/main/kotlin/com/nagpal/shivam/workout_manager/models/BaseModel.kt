package com.nagpal.shivam.workout_manager.models

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
open class BaseModel {
    @Id
    val id: Long? = null

    @Column(columnDefinition = "uuid", updatable = false)
    val uuid: String? = null
    val deleted: Boolean? = null

    @CreationTimestamp
    @Column(columnDefinition = "DATE")
    val timeCreated: LocalDateTime? = null

    @UpdateTimestamp
    @Column(columnDefinition = "DATE")
    val timeLastModified: LocalDateTime? = null
}
