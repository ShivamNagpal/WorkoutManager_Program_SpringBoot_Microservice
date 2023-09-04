package com.nagpal.shivam.workout.manager.utils

object ErrorMessages {
    const val AUTHORIZATION_HEADER_MUST_BE_PRESENT = "Authorization header must be present"
    const val ACCESS_DENIED = "Access Denied"
    const val INVALID_JWT: String = "Invalid JWT"
    const val TOKEN_MUST_START_WITH_BEARER_SCHEME: String = "Token must start with the 'Bearer' scheme"
    const val STAGE_UUID_DOES_NOT_EXISTS: String = "Stage UUID doesn't exists"
    const val PROGRAM_UUID_NOT_FOUND: String = "Program UUID doesn't exists"
    const val ERROR_VALIDATING_THE_FIELDS: String = "Error while validating the request body"
    const val WORKOUT_UUID_DOES_NOT_EXISTS: String = "Workout uuid does not exists"
    const val SECTION_UUID_DOES_NOT_EXISTS: String = "Section uuid does not exists"
    const val DRILL_UUID_DOES_NOT_EXISTS: String = "Drill uuid does not exists"
    const val REORDER_ENTRIES_COUNT_MISMATCH: String = "Reorder entries count mismatch"
    const val REORDER_ENTRIES_VALUE_MISMATCH: String = "Reorder entries value mismatch"
    fun invalidWorkoutLevel(value: String): String = "$value is an invalid workout level"
    fun invalidDrillLengthUnit(value: String): String = "$value is an invalid drill length unit"
}
