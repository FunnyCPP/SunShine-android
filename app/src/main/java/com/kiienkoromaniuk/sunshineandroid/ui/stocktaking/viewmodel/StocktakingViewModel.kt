package com.kiienkoromaniuk.sunshineandroid.ui.stocktaking.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kiienkoromaniuk.sunshineandroid.data.State
import com.kiienkoromaniuk.sunshineandroid.data.model.Item
import com.kiienkoromaniuk.sunshineandroid.data.model.StocktakingItem
import com.kiienkoromaniuk.sunshineandroid.data.model.StocktakingItemType
import com.kiienkoromaniuk.sunshineandroid.data.repository.MainRepository
import com.kiienkoromaniuk.sunshineandroid.data.request.StocktakingRequest
import com.kiienkoromaniuk.sunshineandroid.data.response.ItemsResponse
import com.kiienkoromaniuk.sunshineandroid.ui.additemscreen.helper.DateHelper
import com.kiienkoromaniuk.sunshineandroid.ui.stocktaking.state.StocktakingState
import com.kiienkoromaniuk.sunshineandroid.view.extensions.SingleSharedFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.ZoneId
import javax.inject.Inject

@HiltViewModel
class StocktakingViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    var isGetRequiredItemsWasCalled = false
    private val _stocktakingState = MutableStateFlow(
        StocktakingState(),
    )
    val stocktakingState = _stocktakingState.asStateFlow()

    private val _requiredItemsRequest: MutableSharedFlow<Pair<String, String>> = SingleSharedFlow()
    val requiredItemsState: LiveData<State<ItemsResponse>> =
        _requiredItemsRequest.flatMapLatest { request ->
            mainRepository.getItemsByHouseAndRoom(request.first, request.second)
        }.asLiveData(Dispatchers.IO)

    private val _itemRequest: MutableSharedFlow<String> = SingleSharedFlow()
    val itemState: LiveData<State<Item>> = _itemRequest.flatMapLatest { request ->
        mainRepository.getItemByCode(request)
    }.asLiveData(Dispatchers.IO)

    private val _stocktakingRequest: MutableSharedFlow<StocktakingRequest> = SingleSharedFlow()
    val stocktaking: LiveData<State<Unit>> = _stocktakingRequest.flatMapLatest { request ->
        mainRepository.createStocktaking(request)
    }.asLiveData(Dispatchers.IO)

    fun getItemsByHouseAndRoom(house: String, room: String) {
        Log.e("TAGG", "getItemsByHouseAndRoom called")
        viewModelScope.launch {
            _requiredItemsRequest.emit(Pair(house, room))
        }
    }

    fun getItemByCode(code: String) {
        viewModelScope.launch {
            _itemRequest.emit(code)
        }
    }

    fun updateRequiredItems(requiredItems: List<Item>) {
        _stocktakingState.update { state ->
            state.copy(requiredItems = requiredItems)
        }
    }

    fun updateAddedItems(addedItems: List<Item>) {
        _stocktakingState.update { state ->
            state.copy(addedItems = addedItems)
        }
    }

    fun updateRemainingItems(remainingItems: List<Item>) {
        _stocktakingState.update { state ->
            state.copy(remainingItems = remainingItems)
        }
    }

    fun updateAdditionalItems(additionalItems: List<Item>) {
        _stocktakingState.update { state ->
            state.copy(additionalItems = additionalItems)
        }
    }

    fun createStocktaking(house: String, room: String) {
        val  stocktakingState = stocktakingState.value
        val createdAt = DateHelper.dateWithDashesFromMilliseconds(
            LocalDateTime.now()
                .atZone(ZoneId.systemDefault())
                .toInstant()
                .epochSecond
        ).orEmpty()
        val items: MutableList<StocktakingItem> = mutableListOf()
        stocktakingState.addedItems.forEach {
            when{
                stocktakingState.requiredItems.contains(it) -> {
                    it.id?.let {id->
                        items.add(StocktakingItem(StocktakingItemType.REQUIRED_ITEM,id))
                    }
                }
                stocktakingState.additionalItems.contains(it) -> {
                    it.id?.let {id->
                        items.add(StocktakingItem(StocktakingItemType.ADDITIONAL_ITEM,id))
                    }
                }
            }
        }
        stocktakingState.remainingItems.forEach {
            it.id?.let {id->
                items.add(StocktakingItem(StocktakingItemType.MISSING_ITEM,id))
            }
        }
        viewModelScope.launch {
            _stocktakingRequest.emit(StocktakingRequest(
                house = house,
                room = room,
                createdAt = createdAt,
                items = items
            ))
        }

    }
}