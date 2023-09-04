package com.nagpal.shivam.workout.manager.dtos.transformers

import com.nagpal.shivam.workout.manager.dtos.request.SectionDeepSaveRequestDto
import com.nagpal.shivam.workout.manager.dtos.request.SectionRequestDto
import com.nagpal.shivam.workout.manager.dtos.response.SectionResponseDto
import com.nagpal.shivam.workout.manager.models.Section
import com.nagpal.shivam.workout.manager.models.Workout
import org.springframework.stereotype.Component

@Component
class SectionTransformer {

    fun convertSectionRequestDtoToSection(sectionRequestDto: SectionRequestDto, workout: Workout): Section {
        val section = Section()
        return section.apply {
            this.workout = workout
            workoutId = workout.id
            name = sectionRequestDto.name
            repetitions = sectionRequestDto.repetitions
            restingInfo = sectionRequestDto.restingInfo
        }
    }

    fun convertSectionDeepSaveRequestDtoToSection(
        sectionDeepSaveRequestDto: SectionDeepSaveRequestDto,
        workout: Workout,
    ): Section {
        val section = Section()
        return section.apply {
            this.workout = workout
            workoutId = workout.id
            name = sectionDeepSaveRequestDto.name
            repetitions = sectionDeepSaveRequestDto.repetitions
            restingInfo = sectionDeepSaveRequestDto.restingInfo
        }
    }

    fun convertSectionToSectionResponseDto(section: Section): SectionResponseDto {
        return SectionResponseDto(
            section.id,
            section.workoutId,
            section.name,
            section.repetitions,
            section.restingInfo,
            section.order,
        )
    }
}
