package com.nagpal.shivam.workout_manager.services.impl

import com.nagpal.shivam.workout_manager.dtos.request.ReorderRequestDto
import com.nagpal.shivam.workout_manager.dtos.request.StageRequestDto
import com.nagpal.shivam.workout_manager.dtos.request.StageWorkoutRequestDto
import com.nagpal.shivam.workout_manager.dtos.response.StageResponseDto
import com.nagpal.shivam.workout_manager.dtos.response.StageWorkoutResponseDto
import com.nagpal.shivam.workout_manager.dtos.transformers.StageTransformer
import com.nagpal.shivam.workout_manager.dtos.transformers.StageWorkoutTransformer
import com.nagpal.shivam.workout_manager.exceptions.ResponseException
import com.nagpal.shivam.workout_manager.helpers.impl.ReorderHelper
import com.nagpal.shivam.workout_manager.repositories.ProgramRepository
import com.nagpal.shivam.workout_manager.repositories.StageRepository
import com.nagpal.shivam.workout_manager.repositories.StageWorkoutRepository
import com.nagpal.shivam.workout_manager.repositories.WorkoutRepository
import com.nagpal.shivam.workout_manager.services.IStageService
import com.nagpal.shivam.workout_manager.utils.ErrorMessages
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class StageService @Autowired constructor(
    private val stageRepository: StageRepository,
    private val stageTransformer: StageTransformer,
    private val programRepository: ProgramRepository,
    private val workoutRepository: WorkoutRepository,
    private val stageWorkoutTransformer: StageWorkoutTransformer,
    private val stageWorkoutRepository: StageWorkoutRepository,
    private val reorderHelper: ReorderHelper,
) : IStageService {
    override fun saveStage(stageRequestDto: StageRequestDto): StageResponseDto {
        val programOptional = programRepository.findByIdAndDeleted(stageRequestDto.programId!!)
        if (programOptional.isEmpty) {
            throw ResponseException(HttpStatus.BAD_REQUEST, ErrorMessages.PROGRAM_UUID_NOT_FOUND)
        }
        val program = programOptional.get()
        var stage = stageTransformer.convertStageRequestDtoToStage(stageRequestDto, program)
        val maxCount = stageRepository.fetchMaxCount(stage.programId!!).orElse(0)
        stage.order = maxCount + 1
        stage = stageRepository.save(stage)
        return stageTransformer.convertStageToStageResponseDto(stage)
    }

    override fun linkWorkout(stageWorkoutRequestDto: StageWorkoutRequestDto): StageWorkoutResponseDto {
        val stageOptional = stageRepository.findByIdAndDeleted(stageWorkoutRequestDto.stageId!!)
        if (stageOptional.isEmpty) {
            throw ResponseException(HttpStatus.BAD_REQUEST, ErrorMessages.STAGE_UUID_DOES_NOT_EXISTS)
        }
        val workoutOptional = workoutRepository.findByIdAndDeleted(stageWorkoutRequestDto.workoutId!!)
        if (workoutOptional.isEmpty) {
            throw ResponseException(HttpStatus.BAD_REQUEST, ErrorMessages.WORKOUT_UUID_DOES_NOT_EXISTS)
        }
        val stage = stageOptional.get()
        val workout = workoutOptional.get()
        var stageWorkout =
            stageWorkoutTransformer.convertStageWorkoutRequestDtoToStageWorkout(stageWorkoutRequestDto, stage, workout)
        val maxCount = stageWorkoutRepository.fetchMaxCount(stage.id!!).orElse(0)
        stageWorkout.order = maxCount + 1
        stageWorkout = stageWorkoutRepository.save(stageWorkout)
        return stageWorkoutTransformer.convertStageWorkoutToStageWorkoutResponseDto(stageWorkout)
    }

    override fun getStageById(id: Long): StageResponseDto {
        val stageOptional = stageRepository.findById(id)
        if (stageOptional.isEmpty) {
            throw ResponseException(HttpStatus.BAD_REQUEST, ErrorMessages.STAGE_UUID_DOES_NOT_EXISTS)
        }
        val stage = stageOptional.get()
        val programOptional = programRepository.findByIdAndDeleted(stage.programId!!)
        // If the Program related to stage has been deleted, still show the user that the stage doesn't exists
        if (programOptional.isEmpty) {
            throw ResponseException(HttpStatus.BAD_REQUEST, ErrorMessages.STAGE_UUID_DOES_NOT_EXISTS)
        }
        return stageTransformer.convertStageToStageResponseDto(stage)
    }

    override fun getStages(page: Int, size: Int): List<StageResponseDto> {
        val pageRequest = PageRequest.of(page, size)
        val stages = stageRepository.findAllByDeleted(pageRequest)
        return stages.map { stageTransformer.convertStageToStageResponseDto(it) }
    }

    override fun getStagesInProgram(programId: Long): List<StageResponseDto> {
        val stages = stageRepository.findAllByProgramIdAndDeleted(programId)
        return stages.map { stageTransformer.convertStageToStageResponseDto(it) }
    }

    override fun reorderStages(programId: Long, reorderRequestDto: ReorderRequestDto): List<StageResponseDto> {
        val stages = stageRepository.findAllByProgramIdAndDeleted(programId)
        reorderHelper.reorderItems(reorderRequestDto, stages)
        val savedStages = stageRepository.saveAll(stages)
        return savedStages.sortedBy { it.order }.map { stageTransformer.convertStageToStageResponseDto(it) }
    }
}
