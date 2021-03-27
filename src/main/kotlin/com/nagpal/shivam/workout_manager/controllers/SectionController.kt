package com.nagpal.shivam.workout_manager.controllers

import com.nagpal.shivam.workout_manager.dtos.request.SectionDrillRequestDto
import com.nagpal.shivam.workout_manager.dtos.request.SectionRequestDto
import com.nagpal.shivam.workout_manager.dtos.response.ResponseWrapper
import com.nagpal.shivam.workout_manager.dtos.response.SectionDrillResponseDto
import com.nagpal.shivam.workout_manager.dtos.response.SectionResponseDto
import com.nagpal.shivam.workout_manager.services.impl.SectionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/section")
class SectionController @Autowired constructor(
    private val sectionService: SectionService
) {

    @PostMapping
    fun saveSection(@RequestBody @Valid sectionRequestDto: SectionRequestDto): ResponseEntity<ResponseWrapper<SectionResponseDto>> {
        val sectionResponseDto = sectionService.saveSection(sectionRequestDto)
        return ResponseEntity.ok(ResponseWrapper(sectionResponseDto))
    }

    @PostMapping("/link-drill")
    fun linkDrill(@RequestBody @Valid sectionDrillRequestDto: SectionDrillRequestDto): ResponseEntity<ResponseWrapper<SectionDrillResponseDto>> {
        val sectionDrillResponseDto = sectionService.linkWorkout(sectionDrillRequestDto)
        return ResponseEntity.ok(ResponseWrapper(sectionDrillResponseDto))
    }
}
