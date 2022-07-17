package com.nagpal.shivam.workout_manager

import com.nagpal.shivam.workout_manager.utils.UtilMethods
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WorkoutManagerApplication

fun main(args: Array<String>) {
    UtilMethods.validateNonDuplicateResponseCodes()
    runApplication<WorkoutManagerApplication>(*args)
}
