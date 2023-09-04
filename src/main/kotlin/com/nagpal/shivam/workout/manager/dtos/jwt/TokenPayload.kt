package com.nagpal.shivam.workout.manager.dtos.jwt

data class TokenPayload(val userId: Long, val roles: List<String>)
