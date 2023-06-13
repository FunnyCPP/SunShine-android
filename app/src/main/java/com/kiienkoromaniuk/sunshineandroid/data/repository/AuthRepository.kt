package com.kiienkoromaniuk.sunshineandroid.data.repository

import com.kiienkoromaniuk.sunshineandroid.data.State
import com.kiienkoromaniuk.sunshineandroid.data.model.AccessTokenValidity
import com.kiienkoromaniuk.sunshineandroid.data.model.LoginRequest
import com.kiienkoromaniuk.sunshineandroid.data.model.RefreshTokenResponse
import com.kiienkoromaniuk.sunshineandroid.data.request.RegisterRequest
import com.kiienkoromaniuk.sunshineandroid.source.local.SharedPreferencesManager
import com.kiienkoromaniuk.sunshineandroid.source.remote.client.NoAuthModelClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import java.time.LocalDateTime
import java.time.ZoneId
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val noAuthModelClient: NoAuthModelClient,
    private val sharedPreferencesManager: SharedPreferencesManager,
) : BaseRepository() {

    fun getAccessTokenOrNull(): String? {
        val encryptedAccessToken =
            sharedPreferencesManager.loadStringOrNull(SharedPreferencesManager.Key.ACCESS_TOKEN)
        if (encryptedAccessToken == null) {
            val accessToken =
                sharedPreferencesManager.loadStringOrNull(SharedPreferencesManager.Key.ACCESS_TOKEN)
            accessToken?.let { saveAccessToken(it) }
            return accessToken
        }
        return encryptedAccessToken
    }

    private fun saveAccessToken(accessToken: String) {
        sharedPreferencesManager.save(
            accessToken,
            SharedPreferencesManager.Key.ACCESS_TOKEN,
        )
    }

    fun getTokenType() =
        sharedPreferencesManager.loadStringOrNull(SharedPreferencesManager.Key.TOKEN_TYPE)

    fun isAuthTokenValid(): AccessTokenValidity {
        val accessToken = getAccessTokenOrNull()
        val expiresInSeconds =
            sharedPreferencesManager.loadStringOrNull(SharedPreferencesManager.Key.EXPIRES_IN)
                ?.toLongOrNull()
        val createdAtSeconds =
            sharedPreferencesManager.loadStringOrNull(SharedPreferencesManager.Key.CREATED_AT)
                ?.toLongOrNull()
        if (accessToken.isNullOrEmpty() || expiresInSeconds == null || createdAtSeconds == null) {
            return AccessTokenValidity.INVALID
        }
        val todayMinusOneWeek = LocalDateTime.now()
            .atZone(ZoneId.systemDefault())
            .toInstant()
            .epochSecond
        val expiresAt = createdAtSeconds + expiresInSeconds
        if (expiresAt < todayMinusOneWeek) {
            return AccessTokenValidity.EXPIRED
        }
        return AccessTokenValidity.VALID
    }

    fun saveRefreshTokenData(response: RefreshTokenResponse) {
        sharedPreferencesManager.save("Bearer", SharedPreferencesManager.Key.TOKEN_TYPE)
        saveAccessToken(response.accessToken.orEmpty())
        sharedPreferencesManager.save(
            (System.currentTimeMillis() / 1000).toString(),
            SharedPreferencesManager.Key.CREATED_AT,
        )
        sharedPreferencesManager.save(
            86400.toString(),
            SharedPreferencesManager.Key.EXPIRES_IN,
        )
    }

    suspend fun loginUser(loginRequest: LoginRequest) = emitResponse {
        noAuthModelClient.login(loginRequest)
    }

    suspend fun register(loginRequest: LoginRequest) = emitResponse {
        noAuthModelClient.register(
            RegisterRequest(
                firstname = loginRequest.email.orEmpty(),
                lastname = loginRequest.email.orEmpty(),
                email = loginRequest.email.orEmpty(),
                password = loginRequest.password.orEmpty(),
            )
        )
    }

}