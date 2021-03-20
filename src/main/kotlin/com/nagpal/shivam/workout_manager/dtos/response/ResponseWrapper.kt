package com.nagpal.shivam.workout_manager.dtos.response

data class ResponseWrapper<T>(val payload: T, val message: String? = null, val success: Boolean = true)
