package com.nagpal.shivam.workout.manager.services.impl

import com.nagpal.shivam.workout.manager.dtos.request.ReorderRequestDto
import com.nagpal.shivam.workout.manager.dtos.request.SectionDrillRequestDto
import com.nagpal.shivam.workout.manager.dtos.request.SectionRequestDto
import com.nagpal.shivam.workout.manager.dtos.response.SectionDrillResponseDto
import com.nagpal.shivam.workout.manager.dtos.response.SectionResponseDto
import com.nagpal.shivam.workout.manager.dtos.transformers.SectionDrillTransformer
import com.nagpal.shivam.workout.manager.dtos.transformers.SectionTransformer
import com.nagpal.shivam.workout.manager.exceptions.ResponseException
import com.nagpal.shivam.workout.manager.helpers.impl.ReorderHelper
import com.nagpal.shivam.workout.manager.repositories.DrillRepository
import com.nagpal.shivam.workout.manager.repositories.SectionDrillRepository
import com.nagpal.shivam.workout.manager.repositories.SectionRepository
import com.nagpal.shivam.workout.manager.repositories.WorkoutRepository
import com.nagpal.shivam.workout.manager.services.ISectionService
import com.nagpal.shivam.workout.manager.utils.ErrorMessages
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class SectionService @Autowired constructor(
    private val workoutRepository: WorkoutRepository,
    private val sectionRepository: SectionRepository,
    private val drillRepository: DrillRepository,
    private val sectionDrillRepository: SectionDrillRepository,
    private val sectionTransformer: SectionTransformer,
    private val sectionDrillTransformer: SectionDrillTransformer,
    private val reorderHelper: ReorderHelper,
) : ISectionService {
    override fun saveSection(sectionRequestDto: SectionRequestDto): SectionResponseDto {
        val workoutOptional = workoutRepository.findByIdAndDeleted(sectionRequestDto.workoutId!!)
        if (workoutOptional.isEmpty) {
            throw ResponseException(HttpStatus.BAD_REQUEST, ErrorMessages.WORKOUT_UUID_DOES_NOT_EXISTS)
        }
        val workout = workoutOptional.get()
        var section = sectionTransformer.convertSectionRequestDtoToSection(sectionRequestDto, workout)
        val maxCount = sectionRepository.fetchMaxCount(workout.id!!).orElse(0)
        section.order = maxCount + 1
        section = sectionRepository.save(section)
        return sectionTransformer.convertSectionToSectionResponseDto(section)
    }

    override fun linkWorkout(sectionDrillRequestDto: SectionDrillRequestDto): SectionDrillResponseDto {
        val sectionOptional = sectionRepository.findByIdAndDeleted(sectionDrillRequestDto.sectionId!!)
        if (sectionOptional.isEmpty) {
            throw ResponseException(HttpStatus.BAD_REQUEST, ErrorMessages.SECTION_UUID_DOES_NOT_EXISTS)
        }
        val drillOptional = drillRepository.findByIdAndDeleted(sectionDrillRequestDto.drillId!!)
        if (drillOptional.isEmpty) {
            throw ResponseException(HttpStatus.BAD_REQUEST, ErrorMessages.DRILL_UUID_DOES_NOT_EXISTS)
        }
        val section = sectionOptional.get()
        val drill = drillOptional.get()
        var sectionDrill = try {
            sectionDrillTransformer.convertSectionDrillRequestDtoToSection(sectionDrillRequestDto, section, drill)
        } catch (e: IllegalArgumentException) {
            throw ResponseException(
                HttpStatus.BAD_REQUEST,
                ErrorMessages.invalidDrillLengthUnit(sectionDrillRequestDto.units!!),
            )
        }
        val maxCount = sectionDrillRepository.fetchMaxCount(section.id!!).orElse(0)
        sectionDrill.order = maxCount + 1
        sectionDrill = sectionDrillRepository.save(sectionDrill)
        return sectionDrillTransformer.convertSectionDrillToSectionDrillResponseDto(
            sectionDrill,
        )
    }

    override fun reorderSections(workoutId: Long, reorderRequestDto: ReorderRequestDto): List<SectionResponseDto> {
        val sections = sectionRepository.findByWorkoutId(workoutId)
        reorderHelper.reorderItems(reorderRequestDto, sections) {
            return@reorderItems it.id!!
        }
        sectionRepository.saveAll(sections)
        return sections.sortedBy { it.order }.map { sectionTransformer.convertSectionToSectionResponseDto(it) }
    }
}
