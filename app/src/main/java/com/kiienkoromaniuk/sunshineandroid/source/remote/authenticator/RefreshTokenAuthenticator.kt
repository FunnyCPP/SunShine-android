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
            authRepository.refreshTokenWithOutCallback(getRefreshTokenRequest())
            val tokenType = authRepository.getTokenType()
            val accessToken = authRepository.getAccessTokenOrNull()
            response.request.newBuilder()
                .removeHeader(HttpHeaders.AUTHORIZATION)
                .addHeader(HttpHeaders.AUTHORIZATION, "$tokenType $accessToken")
                .build()
        } catch (e: Throwable) {
            null
        }
    }

    private fun getRefreshTokenRequest() = RefreshTokenRequest().apply {
        refreshToken = authRepository.getRefreshTokenOrNull()
        grantType = RefreshTokenRequest.GRANT_TYPE
        clientId = authRepository.getDomain()
    }
}
