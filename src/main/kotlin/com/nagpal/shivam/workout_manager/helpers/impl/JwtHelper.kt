package com.nagpal.shivam.workout_manager.helpers.impl

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import com.nagpal.shivam.workout_manager.dtos.jwt.TokenPayload
import com.nagpal.shivam.workout_manager.exceptions.ResponseException
import com.nagpal.shivam.workout_manager.helpers.IJwtHelper
import com.nagpal.shivam.workout_manager.utils.Constants
import com.nagpal.shivam.workout_manager.utils.ErrorMessages
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import java.security.KeyFactory
import java.security.interfaces.RSAPublicKey
import java.security.spec.X509EncodedKeySpec
import java.util.*

@Component
class JwtHelper @Autowired constructor(@Value("\${auth.token.public.key}") val publicKeyString: String) : IJwtHelper {
    private val jwtVerifier: JWTVerifier

    init {
        jwtVerifier = JWT.require(getRSAAlgorithm()).build()
    }

    private fun getRSAAlgorithm(): Algorithm {
        val decoder = Base64.getDecoder()
        val publicKeyBytes: ByteArray = decoder.decode(publicKeyString)
        val keyFactory = KeyFactory.getInstance(Constants.RSA)
        val publicKey = keyFactory.generatePublic(X509EncodedKeySpec(publicKeyBytes)) as RSAPublicKey
        return Algorithm.RSA512(publicKey, null)
    }

    override fun verifyAndDecode(token: String): TokenPayload {
        if (!token.startsWith(Constants.BEARER)) {
            throw ResponseException(HttpStatus.BAD_REQUEST, ErrorMessages.TOKEN_MUST_START_WITH_BEARER_SCHEME)
        }
        try {
            val jwt = token.substring(7)
            val decodedJWT: DecodedJWT = jwtVerifier.verify(jwt)
            return TokenPayload(
                decodedJWT.getClaim(Constants.USER_ID).asLong(),
                decodedJWT.getClaim(Constants.ROLES).asList(String::class.java)
            )
        } catch (e: Exception) {
            throw ResponseException(HttpStatus.UNAUTHORIZED, ErrorMessages.INVALID_JWT)
        }
    }
}
