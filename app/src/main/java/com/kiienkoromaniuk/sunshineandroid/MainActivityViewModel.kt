package com.kiienkoromaniuk.sunshineandroid

import androidx.lifecycle.ViewModel
import com.kiienkoromaniuk.sunshineandroid.data.model.AccessTokenValidity
import com.kiienkoromaniuk.sunshineandroid.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    fun isTokenValid(): AccessTokenValidity {
        return authRepository.isAuthTokenValid()
    }
}
