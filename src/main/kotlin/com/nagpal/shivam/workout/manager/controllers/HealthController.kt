package com.nagpal.shivam.workout.manager.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthController {
    @GetMapping("/health")
    fun health(): String {
        throw RuntimeException("asdf")
    }
}