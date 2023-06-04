package com.kiienkoromaniuk.sunshineandroid.source.remote.interceptor

import com.kiienkoromaniuk.sunshineandroid.data.repository.AuthRepository
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class AuthorizationHeaderInterceptor  @Inject constructor(
    private val authRepository: AuthRepository,
) : Interceptor {

    companion object {
        private const val AUTHORIZATION_LANGUAGE_HEADER = "Authorization"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        if (originalRequest.hasAuthorizationHeader()) {
            return chain.proceed(originalRequest)
        }

        val tokenType = authRepository.getTokenType()
        val accessToken = authRepository.getAccessTokenOrNull()

        val requestWithHeader = originalRequest.newBuilder()
            .addHeader(AUTHORIZATION_LANGUAGE_HEADER, "$tokenType $accessToken")
            .build()
        return chain.proceed(requestWithHeader)
    }

    private fun Request.hasAuthorizationHeader() =
        this.header(AUTHORIZATION_LANGUAGE_HEADER) != null
}
