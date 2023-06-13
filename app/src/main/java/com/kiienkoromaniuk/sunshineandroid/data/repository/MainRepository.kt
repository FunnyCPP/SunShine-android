package com.kiienkoromaniuk.sunshineandroid.data.repository

import com.kiienkoromaniuk.sunshineandroid.data.model.Item
import com.kiienkoromaniuk.sunshineandroid.data.request.StocktakingRequest
import com.kiienkoromaniuk.sunshineandroid.source.remote.client.ModelClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val modelClient: ModelClient,
): BaseRepository(){
    suspend fun getItems() = emitResponse {
        modelClient.getItems()
    }

    suspend fun createItem(item: Item) = emitResponse {
        modelClient.createItem(item = item)
    }

    suspend fun getItem(id: Long) = emitResponse {
        modelClient.getItem(id)
    }

    suspend fun getItemsByHouseAndRoom(house: String, room: String) = emitResponse {
        modelClient.getItemsByHouseAndRoom(house,room)
    }

    suspend fun getItemByCode(code: String) = emitResponse {
        modelClient.getItemByCode(code)
    }

    suspend fun getStocktaking() = emitResponse {
        modelClient.getStocktaking()
    }

    suspend fun createStocktaking(stocktakingRequest: StocktakingRequest) = emitResponse {
        modelClient.createStocktaking(stocktakingRequest = stocktakingRequest)
    }

    suspend fun getStocktakingById(id: Long) = emitResponse {
        modelClient.getStocktakingById(id)
    }
}