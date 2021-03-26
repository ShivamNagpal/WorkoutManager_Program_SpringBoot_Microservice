package com.nagpal.shivam.workout_manager.services

import com.nagpal.shivam.workout_manager.dtos.request.SectionRequestDto
import com.nagpal.shivam.workout_manager.dtos.response.SectionResponseDto
import org.springframework.transaction.annotation.Transactional

@Transactional
interface ISectionService {
    fun saveSection(sectionRequestDto: SectionRequestDto): SectionResponseDto
}