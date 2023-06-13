package com.kiienkoromaniuk.sunshineandroid.ui.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kiienkoromaniuk.sunshineandroid.data.State
import com.kiienkoromaniuk.sunshineandroid.data.model.LoginRequest
import com.kiienkoromaniuk.sunshineandroid.data.model.RefreshTokenResponse
import com.kiienkoromaniuk.sunshineandroid.data.repository.AuthRepository
import com.kiienkoromaniuk.sunshineandroid.ui.additemscreen.state.AddItemState
import com.kiienkoromaniuk.sunshineandroid.ui.login.state.LoginState
import com.kiienkoromaniuk.sunshineandroid.view.extensions.SingleSharedFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
): ViewModel() {

    private val _loginState = MutableStateFlow(
        LoginState(),
    )
    val loginState = _loginState.asStateFlow()

    private val _loginRequest: MutableSharedFlow<LoginRequest> = SingleSharedFlow()
    val refreshTokenResponse: Flow<State<RefreshTokenResponse>> = _loginRequest.flatMapLatest { request ->
        authRepository.loginUser(request)
    }

    private val _registerRequest: MutableSharedFlow<LoginRequest> = SingleSharedFlow()
    val refreshTokenResponseFromRegister: Flow<State<RefreshTokenResponse>> = _registerRequest.flatMapLatest { request ->
        authRepository.register(request)
    }

    fun updateEmail(email: String) {
        _loginState.update { state ->
            state.copy(email = email)
        }
    }

    fun updatePassword(password: String) {
        _loginState.update { state ->
            state.copy(password = password)
        }
    }

    fun login() {
        viewModelScope.launch {
            _loginRequest.emit(LoginRequest(loginState.value.email, loginState.value.password))
        }
    }

    fun register() {
        viewModelScope.launch {
            _registerRequest.emit(LoginRequest(loginState.value.email, loginState.value.password))
        }
    }

    fun saveRefreshToken(refreshTokenResponse: RefreshTokenResponse){
        authRepository.saveRefreshTokenData(refreshTokenResponse)
    }
}