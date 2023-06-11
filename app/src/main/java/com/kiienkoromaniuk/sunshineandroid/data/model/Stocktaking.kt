package com.kiienkoromaniuk.sunshineandroid.data.model

import com.google.gson.annotations.SerializedName

data class Stocktaking(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("house")
    val house: String?,
    @SerializedName("room")
    val room: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("required_items")
    val requiredItems: List<Item>?,
    @SerializedName("additional_items")
    val additionalItems: List<Item>?,
    @SerializedName("missing_items")
    val missingItems: List<Item>?,
)
