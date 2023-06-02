package com.kiienkoromaniuk.sunshineandroid.ui.stocktakinglisting.viewmodel

import androidx.lifecycle.ViewModel
import com.kiienkoromaniuk.sunshineandroid.ui.stocktakinglisting.state.StocktakingListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class StocktakingListingViewModel : ViewModel() {

    private val _stocktakingListingState = MutableStateFlow(
        StocktakingListState(),
    )
    val stocktakingListingState = _stocktakingListingState.asStateFlow()

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