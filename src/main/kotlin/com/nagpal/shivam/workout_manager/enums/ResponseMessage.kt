package com.nagpal.shivam.workout_manager.enums

import java.text.MessageFormat

enum class ResponseMessage(val messageCode: String, private val message: String) {
    DUPLICATE_RESPONSE_CODES("E-000", "Duplicate response codes defined: {0}"),
    AUTHORIZATION_HEADER_MUST_BE_PRESENT("E-001", "Authorization header must be present"),
    ACCESS_DENIED("E-002", "Access Denied"),
    INVALID_JWT("E-003", "Invalid JWT"),
    TOKEN_MUST_START_WITH_BEARER_SCHEME("E-004", "Token must start with the 'Bearer' scheme"),
    STAGE_UUID_DOES_NOT_EXISTS("E-005", "Stage UUID doesn't exists"),
    PROGRAM_UUID_NOT_FOUND("E-006", "Program UUID doesn't exists"),
    ERROR_VALIDATING_THE_FIELDS("E-007", "Error while validating the request body"),
    WORKOUT_UUID_DOES_NOT_EXISTS("E-008", "Workout uuid does not exists"),
    SECTION_UUID_DOES_NOT_EXISTS("E-009", "Section uuid does not exists"),
    DRILL_UUID_DOES_NOT_EXISTS("E-010", "Drill uuid does not exists"),
    REORDER_ENTRIES_COUNT_MISMATCH("E-011", "Reorder entries count mismatch"),
    REORDER_ENTRIES_VALUE_MISMATCH("E-012", "Reorder entries value mismatch"),
    INVALID_WORKOUT_LEVEL("E-013", "{0} is an invalid workout level"),
    INVALID_DRILL_LENGTH_UNITS("E-014", "{0} is an invalid drill length unit"),
    ;

    fun getMessage(vararg variables: String): String {
        if (variables.isEmpty()) {
            return message
        }
        return MessageFormat.format(message, variables)
    }
}
