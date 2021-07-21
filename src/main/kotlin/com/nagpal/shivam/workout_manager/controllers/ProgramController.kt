package com.nagpal.shivam.workout_manager.controllers

import com.nagpal.shivam.workout_manager.dtos.request.ProgramRequestDto
import com.nagpal.shivam.workout_manager.dtos.response.ProgramResponseDto
import com.nagpal.shivam.workout_manager.dtos.response.ResponseWrapper
import com.nagpal.shivam.workout_manager.services.IProgramService
import com.nagpal.shivam.workout_manager.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/program")
class ProgramController @Autowired constructor(
    private val programService: IProgramService
) {
    @PostMapping
    fun saveProgram(
        @RequestBody @Valid programRequestDto: ProgramRequestDto
    ): ResponseEntity<ResponseWrapper<ProgramResponseDto>> {
        val programResponseDto = programService.saveProgram(programRequestDto)
        return ResponseEntity.ok(ResponseWrapper(programResponseDto))
    }

    @GetMapping("/{id}")
    fun getProgramById(@PathVariable id: Long): ResponseEntity<ResponseWrapper<ProgramResponseDto>> {
        val programResponseDto = programService.getProgramById(id)
        return ResponseEntity.ok(ResponseWrapper(programResponseDto))
    }

    @GetMapping
    fun getProgram(
        @RequestParam(name = Constants.PAGE, defaultValue = Constants.PAGE_DEFAULT_VALUE) page: Int,
        @RequestParam(name = Constants.PAGE_SIZE, defaultValue = Constants.PAGE_SIZE_DEFAULT_VALUE) pageSize: Int
    ): ResponseEntity<ResponseWrapper<List<ProgramResponseDto>>> {
        val programRequestDtos = programService.getPrograms(page, pageSize)
        return ResponseEntity.ok(ResponseWrapper(programRequestDtos))
    }
}
