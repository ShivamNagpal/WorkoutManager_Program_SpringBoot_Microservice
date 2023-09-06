package com.nagpal.shivam.workout.manager.dtos.transformers

import com.nagpal.shivam.workout.manager.dtos.request.DrillDeepSaveRequestDto
import com.nagpal.shivam.workout.manager.dtos.request.SectionDrillRequestDto
import com.nagpal.shivam.workout.manager.dtos.response.SectionDrillResponseDto
import com.nagpal.shivam.workout.manager.enums.DrillLengthUnits
import com.nagpal.shivam.workout.manager.models.Drill
import com.nagpal.shivam.workout.manager.models.Section
import com.nagpal.shivam.workout.manager.models.SectionDrill
import org.springframework.stereotype.Component
import java.util.Locale

@Component
class SectionDrillTransformer {

    fun convertSectionDrillRequestDtoToSection(
        sectionDrillRequestDto: SectionDrillRequestDto,
        section: Section,
        drill: Drill,
    ): SectionDrill {
        val sectionDrill = SectionDrill()
        return sectionDrill.apply {
            this.section = section
            this.drill = drill
            sectionId = section.id
            drillId = drill.id
            length = sectionDrillRequestDto.length
            units = DrillLengthUnits.valueOf(sectionDrillRequestDto.units!!.uppercase(Locale.getDefault()))
            description = sectionDrillRequestDto.description
        }
    }

    fun convertDrillDeepSaveRequestDtoToSectionDrill(
        drillDeepSaveRequestDto: DrillDeepSaveRequestDto,
        section: Section,
        drill: Drill,
    ): SectionDrill {
        val sectionDrill = SectionDrill()
        return sectionDrill.apply {
            this.section = section
            this.drill = drill
            length = drillDeepSaveRequestDto.length
            units = DrillLengthUnits.valueOf(drillDeepSaveRequestDto.units!!.uppercase(Locale.getDefault()))
            description = drillDeepSaveRequestDto.description
        }
    }

    fun convertSectionDrillToSectionDrillResponseDto(
        sectionDrill: SectionDrill,
    ): SectionDrillResponseDto {
        return SectionDrillResponseDto(
            sectionDrill.id,
            sectionDrill.sectionId,
            sectionDrill.drillId,
            sectionDrill.length,
            sectionDrill.units.toString(),
            sectionDrill.description,
        )
    }
}
