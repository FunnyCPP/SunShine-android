package com.kiienkoromaniuk.sunshineandroid.di

import com.kiienkoromaniuk.sunshineandroid.BuildConfig
import com.kiienkoromaniuk.sunshineandroid.source.remote.ServiceGenerator
import com.kiienkoromaniuk.sunshineandroid.source.remote.authenticator.RefreshTokenAuthenticator
import com.kiienkoromaniuk.sunshineandroid.source.remote.client.ModelClient
import com.kiienkoromaniuk.sunshineandroid.source.remote.client.NoAuthModelClient
import com.kiienkoromaniuk.sunshineandroid.source.remote.interceptor.AuthorizationHeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideModelClient(
        authorizationHeaderInterceptor: AuthorizationHeaderInterceptor,
        refreshTokenAuthenticator: RefreshTokenAuthenticator,
    ): ModelClient {
        return ServiceGenerator().createService(
            BuildConfig.API_URL,
            authorizationHeaderInterceptor,
            refreshTokenAuthenticator,
        )
    }

    @Provides
    @Singleton
    fun provideNonAuthModelClient(): NoAuthModelClient {
        return ServiceGenerator().createNoAuthService(BuildConfig.API_URL)
    }
}
