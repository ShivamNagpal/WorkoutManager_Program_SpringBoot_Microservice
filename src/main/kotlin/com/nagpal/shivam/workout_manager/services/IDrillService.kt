package com.nagpal.shivam.workout_manager.services

import com.nagpal.shivam.workout_manager.dtos.request.DrillRequestDto
import com.nagpal.shivam.workout_manager.dtos.response.DrillResponseDto
import org.springframework.transaction.annotation.Transactional

@Transactional
interface IDrillService {
    fun saveDrill(drillRequestDto: DrillRequestDto): DrillResponseDto
}
