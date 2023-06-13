package com.kiienkoromaniuk.sunshineandroid.ui.additemscreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kiienkoromaniuk.sunshineandroid.data.State
import com.kiienkoromaniuk.sunshineandroid.data.model.Item
import com.kiienkoromaniuk.sunshineandroid.data.repository.MainRepository
import com.kiienkoromaniuk.sunshineandroid.ui.additemscreen.state.AddItemState
import com.kiienkoromaniuk.sunshineandroid.view.extensions.SingleSharedFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddItemViewModel @Inject constructor(
    private val mainRepository: MainRepository,
) : ViewModel() {

    private val _addItemState = MutableStateFlow(
        AddItemState(),
    )
    val addItemState = _addItemState.asStateFlow()

    private val _createItemRequest: MutableSharedFlow<Item> = SingleSharedFlow()
    val createItemResponse: Flow<State<Unit>> = _createItemRequest.flatMapLatest { request ->
        mainRepository.createItem(request)
    }

    fun createItem() {
        viewModelScope.launch {
            _createItemRequest.emit(
                Item(
                    id = null,
                    title = addItemState.value.title,
                    room = addItemState.value.room,
                    purchasingDate = addItemState.value.purchasingDate,
                    scrappingDate = addItemState.value.scrappingDate,
                    description = addItemState.value.description,
                    house = addItemState.value.house,
                    code = addItemState.value.barcode,
                ),
            )
        }
    }

    fun updateRoom(room: String) {
        _addItemState.update { state ->
            state.copy(room = room)
        }
    }

    fun updateHouse(house: String) {
        _addItemState.update { state ->
            state.copy(house = house)
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

    fun updateDescription(description: String) {
        _addItemState.update { state ->
            state.copy(description = description)
        }
    }

    fun updateBarcode(barcode: String) {
        _addItemState.update { state ->
            state.copy(barcode = barcode)
        }
    }
}
