package com.nagpal.shivam.workout_manager.models

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.Generated
import org.hibernate.annotations.GenerationTime
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@MappedSuperclass
open class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Generated(value = GenerationTime.INSERT)
    @Column(columnDefinition = "uuid", insertable = false, updatable = false)
    val uuid: UUID? = null
    val deleted: Boolean = false

    @CreationTimestamp
    @Column(columnDefinition = "DATE")
    val timeCreated: LocalDateTime? = null

    @UpdateTimestamp
    @Column(columnDefinition = "DATE")
    val timeLastModified: LocalDateTime? = null
}
