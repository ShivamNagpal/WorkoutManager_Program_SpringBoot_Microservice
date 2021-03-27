package com.nagpal.shivam.workout_manager.services.impl

import com.nagpal.shivam.workout_manager.dtos.request.DrillRequestDto
import com.nagpal.shivam.workout_manager.dtos.response.DrillResponseDto
import com.nagpal.shivam.workout_manager.models.Drill
import com.nagpal.shivam.workout_manager.repositories.DrillRepository
import com.nagpal.shivam.workout_manager.services.IDrillService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DrillService @Autowired constructor(
    private val drillRepository: DrillRepository
) : IDrillService {
    override fun saveDrill(drillRequestDto: DrillRequestDto): DrillResponseDto {
        var drill = Drill(drillRequestDto)
        drill = drillRepository.save(drill)
        return DrillResponseDto(drill)
    }
}
