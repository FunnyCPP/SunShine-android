package com.kiienkoromaniuk.sunshineandroid.di

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
        chuckerHelper: ChuckerHelper,
    ): ModelClient {
        return ServiceGenerator(chuckerHelper).createService(
            BuildConfig.API_URL,
            authorizationHeaderInterceptor,
            refreshTokenAuthenticator,
        )
    }

    @Provides
    @Singleton
    fun provideNonAuthModelClient(
        chuckerHelper: ChuckerHelper,
    ): NoAuthModelClient {
        return ServiceGenerator(chuckerHelper).createNoAuthService(BuildConfig.API_URL)
    }
}