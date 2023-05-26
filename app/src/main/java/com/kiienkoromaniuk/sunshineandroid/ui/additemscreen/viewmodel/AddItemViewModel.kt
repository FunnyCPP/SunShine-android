package com.kiienkoromaniuk.sunshineandroid.ui.additemscreen.viewmodel

import androidx.lifecycle.ViewModel
import com.kiienkoromaniuk.sunshineandroid.ui.additemscreen.state.AddItemState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddItemViewModel : ViewModel() {

    private val _addItemState = MutableStateFlow(
        AddItemState(),
    )
    val addItemState = _addItemState.asStateFlow()

    fun updateRoom(room: String) {
        _addItemState.update { state ->
            state.copy(room = room)
        }
    }

    fun updateTitle(title: String) {
        _addItemState.update { state ->
            state.copy(title = title)
        }
    }

    fun updatePurchasingDate(purchasingDate: String) {
        _addItemState.update { state ->
            state.copy(purchasingDate = purchasingDate)
        }
    }

    fun updateScrappingDate(scrappingDate: String) {
        _addItemState.update { state ->
            state.copy(scrappingDate = scrappingDate)
        }
    }

    fun updateDescription(opis: String) {
        _addItemState.update { state ->
            state.copy(description = opis)
        }
    }
}
