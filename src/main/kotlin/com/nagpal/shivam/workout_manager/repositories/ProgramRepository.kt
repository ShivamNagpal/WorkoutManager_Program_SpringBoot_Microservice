package com.nagpal.shivam.workout_manager.repositories

import com.nagpal.shivam.workout_manager.models.Program
import org.springframework.data.repository.CrudRepository
import java.util.*

interface ProgramRepository : CrudRepository<Program, Long> {
    fun findByUuid(uuid: UUID): Optional<Program>
}
