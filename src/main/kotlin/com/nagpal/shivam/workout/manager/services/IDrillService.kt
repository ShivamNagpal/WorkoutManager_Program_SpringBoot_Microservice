package com.nagpal.shivam.workout.manager.services

import com.nagpal.shivam.workout.manager.dtos.request.DrillRequestDto
import com.nagpal.shivam.workout.manager.dtos.request.ReorderRequestDto
import com.nagpal.shivam.workout.manager.dtos.response.DrillResponseDto
import org.springframework.transaction.annotation.Transactional

@Transactional
interface IDrillService {
    fun saveDrill(drillRequestDto: DrillRequestDto): DrillResponseDto
    fun reorderDrills(sectionId: Long, reorderRequestDto: ReorderRequestDto): List<DrillResponseDto>
}
