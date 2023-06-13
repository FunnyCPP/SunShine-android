package com.kiienkoromaniuk.sunshineandroid.ui.stocktakinglisting.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kiienkoromaniuk.sunshineandroid.data.State
import com.kiienkoromaniuk.sunshineandroid.data.model.Stocktaking
import com.kiienkoromaniuk.sunshineandroid.data.repository.MainRepository
import com.kiienkoromaniuk.sunshineandroid.ui.stocktakinglisting.state.StocktakingListState
import com.kiienkoromaniuk.sunshineandroid.view.extensions.SingleSharedFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StocktakingListingViewModel @Inject constructor(
    private val mainRepository: MainRepository,
) : ViewModel() {

    private val _stocktakingListingState = MutableStateFlow(
        StocktakingListState(),
    )
    val stocktakingListingState = _stocktakingListingState.asStateFlow()

    private val _stocktakingRequest: MutableSharedFlow<Unit> = SingleSharedFlow()
    val stocktaking: Flow<State<List<Stocktaking>>> = _stocktakingRequest.flatMapLatest { request ->
        mainRepository.getStocktaking()
    }

    fun getStocktaking() {
        viewModelScope.launch {
            _stocktakingRequest.emit(Unit)
        }
    }

    fun updateRoom(room: String) {
        _stocktakingListingState.update { state ->
            state.copy(room = room)
        }
    }

    fun updateHouse(house: String) {
        _stocktakingListingState.update { state ->
            state.copy(house = house)
        }
    }

    fun changeDialogVisibility() {
        _stocktakingListingState.update { state ->
            state.copy(shouldShowDialog = !state.shouldShowDialog)
        }
    }
}
