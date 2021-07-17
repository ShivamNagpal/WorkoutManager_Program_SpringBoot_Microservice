package com.nagpal.shivam.workout_manager.repositories

import com.nagpal.shivam.workout_manager.models.Program
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import java.util.*

interface ProgramRepository : CrudRepository<Program, Long> {
    fun findByIdAndDeleted(id: Long, deleted: Boolean = false): Optional<Program>
    fun findByUuidAndDeleted(uuid: UUID, deleted: Boolean = false): Optional<Program>
    fun findAllByDeleted(pageable: Pageable, deleted: Boolean = false): List<Program>
}
