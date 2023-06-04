package com.kiienkoromaniuk.sunshineandroid.source.remote.client

import retrofit2.Response
import retrofit2.http.POST

interface NoAuthModelClient {

    @POST("/api/v1/auth/register")
    suspend fun register(): Response<Unit>

    @POST("/api/v1/auth/login")
    suspend fun login(): Response<Unit>
}