package com.kiienkoromaniuk.sunshineandroid.ui.stocktakingdetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kiienkoromaniuk.sunshineandroid.data.State
import com.kiienkoromaniuk.sunshineandroid.data.model.Stocktaking
import com.kiienkoromaniuk.sunshineandroid.data.repository.MainRepository
import com.kiienkoromaniuk.sunshineandroid.view.extensions.SingleSharedFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StocktakingDetailsViewModel @Inject constructor(
    private val mainRepository: MainRepository
): ViewModel() {

    private val _stocktakingRequest: MutableSharedFlow<Long> = SingleSharedFlow()
    val stocktaking: Flow<State<Stocktaking>> = _stocktakingRequest.flatMapLatest { request ->
        mainRepository.getStocktakingById(request)
    }

    fun getStocktaking(id: Long) {
        viewModelScope.launch {
            _stocktakingRequest.emit(id)
        }
    }
}