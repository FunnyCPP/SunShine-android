package com.kiienkoromaniuk.sunshineandroid.source.remote

import com.kiienkoromaniuk.sunshineandroid.source.remote.authenticator.RefreshTokenAuthenticator
import com.kiienkoromaniuk.sunshineandroid.source.remote.client.ModelClient
import com.kiienkoromaniuk.sunshineandroid.source.remote.client.NoAuthModelClient
import com.kiienkoromaniuk.sunshineandroid.source.remote.interceptor.AuthorizationHeaderInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

private const val TIMEOUT_IN_SECONDS = 40

@Singleton
class ServiceGenerator @Inject constructor(
    private val chuckerHelper: ChuckerHelper,
) {

    fun createService(
        baseUrl: String,
        authorizationHeaderInterceptor: AuthorizationHeaderInterceptor,
        refreshTokenAuthenticator: RefreshTokenAuthenticator,
    ): ModelClient {
        val retrofit = getRxBuilder(baseUrl).client(
            okHttpClient(
                authorizationHeaderInterceptor,
                refreshTokenAuthenticator,
            ),
        ).build()
        return retrofit.create(ModelClient::class.java)
    }

    fun createNoAuthService(baseUrl: String): NoAuthModelClient {
        val retrofit = getRxBuilder(baseUrl).client(okHttpClient()).build()
        return retrofit.create(NoAuthModelClient::class.java)
    }

    fun okHttpClient(
        authorizationHeaderInterceptor: AuthorizationHeaderInterceptor,
        refreshTokenAuthenticator: RefreshTokenAuthenticator,
    ) = OkHttpClient.Builder()
        .readTimeout(TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
        .connectTimeout(TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
        .authenticator(refreshTokenAuthenticator)
        .addInterceptor(authorizationHeaderInterceptor)
        .addInterceptor(chuckerHelper.getChuckerInterceptor())
        .addProfiler()
        .build()

    private fun okHttpClient() = OkHttpClient.Builder()
        .readTimeout(TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
        .connectTimeout(TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
        .build()


}
