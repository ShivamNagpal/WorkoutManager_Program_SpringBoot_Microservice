package com.nagpal.shivam.workout.manager.repositories

import com.nagpal.shivam.workout.manager.models.SectionDrill
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.*

interface SectionDrillRepository : CrudRepository<SectionDrill, Long> {
    @Query(value = "select max(sd.order) from SectionDrill sd where sd.section.id = :sectionId")
    fun fetchMaxCount(sectionId: Long): Optional<Int>

    @Query(value = "select sd from SectionDrill sd where sd.section.id in (:sectionIds) order by sd.order")
    fun findBySectionIds(sectionIds: List<Long>): List<SectionDrill>
    fun findAllBySectionIdAndDeleted(sectionId: Long, deleted: Boolean = false): List<SectionDrill>
}
