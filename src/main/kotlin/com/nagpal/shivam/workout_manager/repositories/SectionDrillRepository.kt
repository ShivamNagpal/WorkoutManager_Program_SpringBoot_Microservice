package com.nagpal.shivam.workout_manager.repositories

import com.nagpal.shivam.workout_manager.models.SectionDrill
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.*

interface SectionDrillRepository : CrudRepository<SectionDrill, Long> {
    @Query(value = "select max(sd.order) from SectionDrill sd where sd.section.id = :sectionId")
    fun fetchMaxCount(sectionId: Long): Optional<Int>
}
