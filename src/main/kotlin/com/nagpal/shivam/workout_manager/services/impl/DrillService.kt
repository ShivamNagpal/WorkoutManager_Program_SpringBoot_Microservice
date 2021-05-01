package com.nagpal.shivam.workout_manager.services.impl

import com.nagpal.shivam.workout_manager.dtos.request.DrillRequestDto
import com.nagpal.shivam.workout_manager.dtos.response.DrillResponseDto
import com.nagpal.shivam.workout_manager.dtos.transformers.DrillTransformer
import com.nagpal.shivam.workout_manager.repositories.DrillRepository
import com.nagpal.shivam.workout_manager.services.IDrillService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DrillService @Autowired constructor(
    private val drillRepository: DrillRepository,
    private val drillTransformer: DrillTransformer,
) : IDrillService {
    override fun saveDrill(drillRequestDto: DrillRequestDto): DrillResponseDto {
        var drill = drillTransformer.convertDrillRequestDtoToDrill(drillRequestDto)
        drill = drillRepository.save(drill)
        return DrillResponseDto(drill)
    }
}
