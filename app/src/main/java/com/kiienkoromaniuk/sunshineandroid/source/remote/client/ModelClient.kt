package com.kiienkoromaniuk.sunshineandroid.source.remote.client

import com.kiienkoromaniuk.sunshineandroid.data.model.*
import com.kiienkoromaniuk.sunshineandroid.data.request.StocktakingRequest
import com.kiienkoromaniuk.sunshineandroid.data.response.BootstrapResponse
import com.kiienkoromaniuk.sunshineandroid.data.response.ItemsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ModelClient {
    @GET("/api/v1/items")
    suspend fun getItems(): Response<ItemsResponse>

    @POST("/api/v1/items")
    suspend fun createItem(@Body item: Item): Response<Unit>

    @GET("/api/v1/items/{id}")
    suspend fun getItem(@Path("id") id: Long): Response<Item>

    @GET("/api/v1/items/code/{code}")
    suspend fun getItemByCode(@Path("code") code: String): Response<Item>

    @GET("/api/v1/stocktaking")
    suspend fun getStocktaking(): Response<List<Stocktaking>>

    @POST("/api/v1/stocktaking")
    suspend fun createStocktaking(@Body stocktakingRequest: StocktakingRequest): Response<Unit>

    @GET("/api/v1/stocktaking/{id}")
    suspend fun getStocktakingById(@Path("id") id: Long): Response<Stocktaking>

    @GET("/api/v1/items")
    suspend fun getItemsByHouseAndRoom(@Query("house") house: String, @Query("room") room: String): Response<ItemsResponse>

    @GET("/api/v1/items/bootstrap")
    suspend fun getBootstrap(): Response<BootstrapResponse>
}