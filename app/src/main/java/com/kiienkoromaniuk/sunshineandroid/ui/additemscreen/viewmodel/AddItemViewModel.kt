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
    fun updatelp(lp: String){
        _addItemState.update { state ->
            state.copy(lp = lp)
        }
    }

    fun updateNazwa(nazwa: String){
        _addItemState.update { state ->
            state.copy(nazwa = nazwa)
        }
    }

    fun updateDataZakupu(data_zakupu: String){
        _addItemState.update { state ->
            state.copy(data_zakupu = data_zakupu)
        }
    }

    fun updateDataZlomowania(data_zlomowania: String){
        _addItemState.update { state ->
            state.copy(data_zlomowania = data_zlomowania)
        }
    }

    fun updateOpis(opis: String){
        _addItemState.update { state ->
            state.copy(opis = opis)
        }
    }
}
