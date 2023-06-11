package com.kiienkoromaniuk.sunshineandroid.data.model

import com.google.gson.annotations.SerializedName

data class StocktakingItem(
    @SerializedName("type")
    val type: StocktakingItemType,
    @SerializedName("item_id")
    val itemId: Long,
)
