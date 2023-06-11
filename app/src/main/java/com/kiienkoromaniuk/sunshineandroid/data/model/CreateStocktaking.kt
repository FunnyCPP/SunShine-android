package com.kiienkoromaniuk.sunshineandroid.data.model

import com.google.gson.annotations.SerializedName

data class CreateStocktaking(
    @SerializedName("house")
    val house: String,
    @SerializedName("room")
    val room: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("items")
    val items: List<StocktakingItem>
)
