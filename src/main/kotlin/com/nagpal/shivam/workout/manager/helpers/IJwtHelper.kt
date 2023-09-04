package com.nagpal.shivam.workout.manager.helpers

import com.nagpal.shivam.workout.manager.dtos.jwt.TokenPayload

interface IJwtHelper {
    fun verifyAndDecode(token: String): TokenPayload
}
