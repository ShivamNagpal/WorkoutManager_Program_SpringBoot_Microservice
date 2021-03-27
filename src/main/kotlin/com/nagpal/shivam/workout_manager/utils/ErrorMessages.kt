package com.nagpal.shivam.workout_manager.utils

interface ErrorMessages {
    companion object {
        const val ERROR_VALIDATING_THE_FIELDS: String = "Error while validating the request body"
        fun INVALID_WORKOUT_LEVEL(value: String): String = "$value is an invalid workout level"
        fun INVALID_DRILL_LENGTH_UNIT(value: String): String = "$value is an invalid drill length unit"
        const val WORKOUT_UUID_DOES_NOT_EXISTS: String = "Workout uuid does not exists"
        const val SECTION_UUID_DOES_NOT_EXISTS: String = "Section uuid does not exists"
        const val DRILL_UUID_DOES_NOT_EXISTS: String = "Drill uuid does not exists"
    }
}
