package com.nagpal.shivam.workout_manager.helpers

import com.nagpal.shivam.workout_manager.dtos.jwt.TokenPayload

interface IJwtHelper {
    fun verifyAndDecode(token: String): TokenPayload
}
