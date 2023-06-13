package com.kiienkoromaniuk.sunshineandroid.source.remote.authenticator

import com.kiienkoromaniuk.sunshineandroid.data.repository.AuthRepository
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class RefreshTokenAuthenticator @Inject constructor(
    private val authRepository: AuthRepository,
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        return try {
            val tokenType = authRepository.getTokenType()
            val accessToken = authRepository.getAccessTokenOrNull()
            response.request.newBuilder()
                .removeHeader("Authorization")
                .addHeader("Authorization", "$tokenType $accessToken")
                .build()
        } catch (e: Throwable) {
            null
        }
    }
}
