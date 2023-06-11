package com.kiienkoromaniuk.sunshineandroid.source.remote.client

import com.kiienkoromaniuk.sunshineandroid.data.model.LoginRequest
import com.kiienkoromaniuk.sunshineandroid.data.model.RefreshTokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface NoAuthModelClient {

    @POST("/api/v1/auth/register")
    suspend fun register(): Response<Unit>

    @POST("/api/v1/auth/authenticate")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<RefreshTokenResponse>
}