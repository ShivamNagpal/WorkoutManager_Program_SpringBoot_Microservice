package com.nagpal.shivam.workout_manager.dtos.response

import com.fasterxml.jackson.annotation.JsonInclude

data class ResponseWrapper<T>(
    @field:JsonInclude(JsonInclude.Include.NON_NULL) val payload: T,
    @field:JsonInclude(JsonInclude.Include.NON_NULL) val message: String? = null,
    val success: Boolean = true
)
