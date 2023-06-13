package com.kiienkoromaniuk.sunshineandroid.data.request

import com.google.gson.annotations.SerializedName
import com.kiienkoromaniuk.sunshineandroid.data.model.StocktakingItem

data class StocktakingRequest(
    @SerializedName("house")
    val house: String,
    @SerializedName("room")
    val room: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("items")
    val items: List<StocktakingItem>
)