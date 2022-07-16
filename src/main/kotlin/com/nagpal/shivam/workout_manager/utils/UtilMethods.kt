package com.nagpal.shivam.workout_manager.utils

import com.nagpal.shivam.workout_manager.enums.ResponseMessage
import com.nagpal.shivam.workout_manager.exceptions.ResponseException
import org.springframework.http.HttpStatus

object UtilMethods {
    fun validateNonDuplicateResponseCodes() {
        var responseMessages = ResponseMessage.values()
        var messageCodes = HashSet<String>()
        for (responseMessage in responseMessages) {
            if (messageCodes.contains(responseMessage.messageCode)) {
                val duplicateResponseMessage = ResponseMessage.DUPLICATE_RESPONSE_CODES
                throw ResponseException(
                    HttpStatus.NOT_ACCEPTABLE,
                    duplicateResponseMessage.messageCode,
                    duplicateResponseMessage.getMessage(responseMessage.messageCode)
                )
            }
            messageCodes.add(responseMessage.messageCode)
        }
    }
}
