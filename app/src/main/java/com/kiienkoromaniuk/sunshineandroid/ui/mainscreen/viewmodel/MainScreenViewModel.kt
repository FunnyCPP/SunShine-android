package com.kiienkoromaniuk.sunshineandroid.ui.mainscreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kiienkoromaniuk.sunshineandroid.data.State
import com.kiienkoromaniuk.sunshineandroid.data.model.LoginRequest
import com.kiienkoromaniuk.sunshineandroid.data.model.RefreshTokenResponse
import com.kiienkoromaniuk.sunshineandroid.data.repository.MainRepository
import com.kiienkoromaniuk.sunshineandroid.data.response.ItemsResponse
import com.kiienkoromaniuk.sunshineandroid.view.extensions.SingleSharedFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val mainRepository: MainRepository
): ViewModel(){

    private val _itemsRequest: MutableSharedFlow<Unit> = SingleSharedFlow()
    val items: Flow<State<ItemsResponse>> = _itemsRequest.flatMapLatest { request ->
        mainRepository.getItems()
    }

    fun getItems() {
        viewModelScope.launch {
            _itemsRequest.emit(Unit)
        }
    }
}