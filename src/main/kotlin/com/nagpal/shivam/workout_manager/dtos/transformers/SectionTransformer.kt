package com.nagpal.shivam.workout_manager.dtos.transformers

import com.nagpal.shivam.workout_manager.dtos.request.SectionDeepSaveRequestDto
import com.nagpal.shivam.workout_manager.dtos.request.SectionRequestDto
import com.nagpal.shivam.workout_manager.dtos.response.SectionResponseDto
import com.nagpal.shivam.workout_manager.models.Section
import com.nagpal.shivam.workout_manager.models.Workout
import org.springframework.stereotype.Component
import java.util.*

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

    fun convertSectionToSectionResponseDto(section: Section, workoutUuid: UUID?): SectionResponseDto {
        return SectionResponseDto(
            section.uuid.toString(),
            workoutUuid?.toString(),
            section.name,
            section.repetitions,
            section.restingInfo,
            section.order
        )
    }
}
