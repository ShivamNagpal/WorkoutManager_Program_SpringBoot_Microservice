package com.nagpal.shivam.workout_manager.services.impl

import com.nagpal.shivam.workout_manager.dtos.request.SectionRequestDto
import com.nagpal.shivam.workout_manager.dtos.response.SectionResponseDto
import com.nagpal.shivam.workout_manager.repositories.SectionRepository
import com.nagpal.shivam.workout_manager.services.ISectionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SectionService @Autowired constructor(
    private val sectionRepository: SectionRepository
) : ISectionService {
    override fun saveSection(sectionRequestDto: SectionRequestDto): SectionResponseDto {
        TODO("Not yet implemented")
    }
}
