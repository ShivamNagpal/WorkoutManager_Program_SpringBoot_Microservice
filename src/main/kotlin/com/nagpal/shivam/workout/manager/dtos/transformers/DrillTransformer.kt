package com.nagpal.shivam.workout.manager.dtos.transformers

import com.nagpal.shivam.workout.manager.dtos.request.DrillDeepSaveRequestDto
import com.nagpal.shivam.workout.manager.dtos.request.DrillRequestDto
import com.nagpal.shivam.workout.manager.dtos.response.DrillResponseDto
import com.nagpal.shivam.workout.manager.models.Drill
import org.springframework.stereotype.Component

@Component
class DrillTransformer {

    fun convertDrillRequestDtoToDrill(drillRequestDto: DrillRequestDto): Drill {
        val drill = Drill()
        return drill.apply {
            name = drillRequestDto.name
        }
    }

    fun convertDrillDeepSaveRequestDtoToDrill(drillDeepSaveRequestDto: DrillDeepSaveRequestDto): Drill {
        val drill = Drill()
        return drill.apply {
            name = drillDeepSaveRequestDto.name
        }
    }

    fun convertDrillToDrillResponseDto(drill: Drill): DrillResponseDto {
        return DrillResponseDto(
            drill.id,
            drill.name,
        )
    }
}
