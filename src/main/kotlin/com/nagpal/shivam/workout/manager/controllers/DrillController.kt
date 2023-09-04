package com.nagpal.shivam.workout.manager.controllers

import com.nagpal.shivam.workout.manager.dtos.request.DrillRequestDto
import com.nagpal.shivam.workout.manager.dtos.request.ReorderRequestDto
import com.nagpal.shivam.workout.manager.dtos.response.DrillResponseDto
import com.nagpal.shivam.workout.manager.dtos.response.ResponseWrapper
import com.nagpal.shivam.workout.manager.services.IDrillService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/drill")
class DrillController @Autowired constructor(
    private val drillService: IDrillService,
) {

    @PostMapping
    fun saveDrill(
        @RequestBody @Valid drillRequestDto: DrillRequestDto,
    ): ResponseEntity<ResponseWrapper<DrillResponseDto>> {
        val drillResponseDto = drillService.saveDrill(drillRequestDto)
        return ResponseEntity.ok(ResponseWrapper(drillResponseDto))
    }

    @PostMapping("/reorder/{sectionId}")
    fun reorderDrills(
        @PathVariable("sectionId") sectionId: Long,
        @RequestBody @Valid reorderRequestDto: ReorderRequestDto,
    ): ResponseEntity<ResponseWrapper<List<DrillResponseDto>>> {
        val drills = drillService.reorderDrills(sectionId, reorderRequestDto)
        return ResponseEntity.ok(ResponseWrapper(drills))
    }
}
