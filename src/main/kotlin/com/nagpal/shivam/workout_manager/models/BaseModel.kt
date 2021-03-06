package com.nagpal.shivam.workout_manager.models

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@MappedSuperclass
class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    val deleted: Boolean = false

    @CreationTimestamp
    @Column(columnDefinition = "DATE")
    val timeCreated: LocalDateTime? = null

    @UpdateTimestamp
    @Column(columnDefinition = "DATE")
    val timeLastModified: LocalDateTime? = null
}
