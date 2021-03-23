package com.nagpal.shivam.workout_manager.repositories

import com.nagpal.shivam.workout_manager.models.Section
import org.springframework.data.repository.CrudRepository

interface SectionRepository : CrudRepository<Section, Long> {
}
