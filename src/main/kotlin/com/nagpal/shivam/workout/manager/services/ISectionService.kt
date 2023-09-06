package com.nagpal.shivam.workout.manager.services

import com.nagpal.shivam.workout.manager.dtos.request.ReorderRequestDto
import com.nagpal.shivam.workout.manager.dtos.request.SectionDrillRequestDto
import com.nagpal.shivam.workout.manager.dtos.request.SectionRequestDto
import com.nagpal.shivam.workout.manager.dtos.response.SectionDrillResponseDto
import com.nagpal.shivam.workout.manager.dtos.response.SectionResponseDto
import org.springframework.transaction.annotation.Transactional

@Transactional
interface ISectionService {
    fun saveSection(sectionRequestDto: SectionRequestDto): SectionResponseDto
    fun linkWorkout(sectionDrillRequestDto: SectionDrillRequestDto): SectionDrillResponseDto
    fun reorderSections(workoutId: Long, reorderRequestDto: ReorderRequestDto): List<SectionResponseDto>
}
