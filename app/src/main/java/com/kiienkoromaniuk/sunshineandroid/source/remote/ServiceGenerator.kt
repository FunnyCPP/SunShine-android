package com.kiienkoromaniuk.sunshineandroid.source.remote

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
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

    fun createRecostreamService(
        baseUrl: String,
        recostreamAuthorizationInterceptor: RecostreamAuthorizationInterceptor,
    ): RecostreamModelClient {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient(recostreamAuthorizationInterceptor))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecostreamModelClient::class.java)
    }

    fun createWebSocketConnection(): WebSocketConnection {
        return WebSocketConnection(okHttpClient())
    }

    private fun getRxBuilder(baseUrl: String): Retrofit.Builder = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(NullOnEmptyConverterFactory())
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder()
                    .serializeNulls()
                    .registerTypeAdapter(
                        FeedItem::class.java,
                        FeedDeserializer(),
                    )
                    .registerTypeAdapter(DataPagination::class.java, DataPaginationDeserializer())
                    .registerTypeAdapter(SummaryResult::class.java, SummaryResultDeserializer())
                    .registerTypeAdapter(
                        PostTemplateResponse::class.java,
                        PostTemplateResponseDeserializer(),
                    )
                    .registerTypeAdapter(Filter::class.java, FilterDeserializer())
                    .registerTypeAdapter(CafeteriaBanner::class.java, CafeteriaBannerDeserializer())
                    .registerTypeAdapter(
                        CafeteriaCarouselItem::class.java,
                        CafeteriaCarouselItemDeserializer(),
                    )
                    .create(),
            ),
        )
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())

    fun okHttpClient(
        authorizationHeaderInterceptor: AuthorizationHeaderInterceptor,
        refreshTokenAuthenticator: RefreshTokenAuthenticator,
    ) = OkHttpClient.Builder()
        .readTimeout(TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
        .connectTimeout(TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
        .authenticator(refreshTokenAuthenticator)
        .addInterceptor(authorizationHeaderInterceptor)
        .addInterceptor(chuckerHelper.getChuckerInterceptor())
        .addInterceptor(AcceptLanguageHeaderInterceptor())
        .addProfiler()
        .build()

    private fun okHttpClient() = OkHttpClient.Builder()
        .readTimeout(TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
        .connectTimeout(TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
        .addInterceptor(AcceptLanguageHeaderInterceptor())
        .addProfiler()
        .build()

    private fun okHttpClient(
        recostreamAuthorizationInterceptor: RecostreamAuthorizationInterceptor,
    ) = OkHttpClient.Builder()
        .readTimeout(TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
        .connectTimeout(TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
        .addInterceptor(chuckerHelper.getChuckerInterceptor())
        .addInterceptor(recostreamAuthorizationInterceptor)
        .addProfiler()
        .build()

    private fun OkHttpClient.Builder.addProfiler(): OkHttpClient.Builder {
        if (BuildConfig.DEBUG) {
            addInterceptor(OkHttpProfilerInterceptor())
        }
        return this
    }
}
