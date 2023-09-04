package com.nagpal.shivam.workout.manager.services.impl

import com.nagpal.shivam.workout.manager.dtos.request.DrillRequestDto
import com.nagpal.shivam.workout.manager.dtos.request.ReorderRequestDto
import com.nagpal.shivam.workout.manager.dtos.response.DrillResponseDto
import com.nagpal.shivam.workout.manager.dtos.transformers.DrillTransformer
import com.nagpal.shivam.workout.manager.helpers.impl.ReorderHelper
import com.nagpal.shivam.workout.manager.repositories.DrillRepository
import com.nagpal.shivam.workout.manager.repositories.SectionDrillRepository
import com.nagpal.shivam.workout.manager.services.IDrillService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DrillService @Autowired constructor(
    private val drillRepository: DrillRepository,
    private val drillTransformer: DrillTransformer,
    private val sectionDrillRepository: SectionDrillRepository,
    private val reorderHelper: ReorderHelper,
) : IDrillService {
    override fun saveDrill(drillRequestDto: DrillRequestDto): DrillResponseDto {
        var drill = drillTransformer.convertDrillRequestDtoToDrill(drillRequestDto)
        drill = drillRepository.save(drill)
        return drillTransformer.convertDrillToDrillResponseDto(drill)
    }

    override fun reorderDrills(sectionId: Long, reorderRequestDto: ReorderRequestDto): List<DrillResponseDto> {
        val sectionDrills = sectionDrillRepository.findAllBySectionIdAndDeleted(sectionId)
        reorderHelper.reorderItems(reorderRequestDto, sectionDrills) {
            return@reorderItems it.drillId!!
        }
        sectionDrillRepository.saveAll(sectionDrills)
        return drillRepository.findAllInASection(sectionId).map { drillTransformer.convertDrillToDrillResponseDto(it) }
    }
}
