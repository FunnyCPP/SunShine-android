package com.kiienkoromaniuk.sunshineandroid.ui.itemdatails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kiienkoromaniuk.sunshineandroid.data.State
import com.kiienkoromaniuk.sunshineandroid.data.model.Item
import com.kiienkoromaniuk.sunshineandroid.data.repository.MainRepository
import com.kiienkoromaniuk.sunshineandroid.view.extensions.SingleSharedFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemDetailsViewModel @Inject constructor(
    private val mainRepository: MainRepository
): ViewModel() {
    private val _itemRequest: MutableSharedFlow<Long> = SingleSharedFlow()
    val itemResponse: Flow<State<Item>> = _itemRequest.flatMapLatest { request ->
        mainRepository.getItem(request)
    }

    fun getItem(id: Long) {
        viewModelScope.launch {
            _itemRequest.emit(id)
        }
    }
}