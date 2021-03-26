package com.nagpal.shivam.workout_manager.services.impl

import com.nagpal.shivam.workout_manager.dtos.request.SectionRequestDto
import com.nagpal.shivam.workout_manager.dtos.response.SectionResponseDto
import com.nagpal.shivam.workout_manager.exceptions.ResponseException
import com.nagpal.shivam.workout_manager.models.Section
import com.nagpal.shivam.workout_manager.repositories.SectionRepository
import com.nagpal.shivam.workout_manager.repositories.WorkoutRepository
import com.nagpal.shivam.workout_manager.services.ISectionService
import com.nagpal.shivam.workout_manager.utils.ErrorMessages
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.*

@Service
class SectionService @Autowired constructor(
    private val workoutRepository: WorkoutRepository,
    private val sectionRepository: SectionRepository
) : ISectionService {
    override fun saveSection(sectionRequestDto: SectionRequestDto): SectionResponseDto {
        val workoutOptional = workoutRepository.findByUuid(UUID.fromString(sectionRequestDto.workoutId!!))
        if (workoutOptional.isEmpty) {
            throw ResponseException(HttpStatus.BAD_REQUEST, ErrorMessages.WORKOUT_UUID_DOES_NOT_EXISTS)
        }
        val workout = workoutOptional.get()
        var section = Section(sectionRequestDto, workout)
        val maxCount = sectionRepository.fetchMaxCount(workout.id!!).orElse(0)
        section.order = maxCount + 1
        section = sectionRepository.save(section)
        return SectionResponseDto(section)
    }
}
