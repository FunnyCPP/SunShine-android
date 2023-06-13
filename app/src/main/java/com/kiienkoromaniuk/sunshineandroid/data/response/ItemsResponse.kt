package com.kiienkoromaniuk.sunshineandroid.data.response

import com.google.gson.annotations.SerializedName
import com.kiienkoromaniuk.sunshineandroid.data.model.Item

data class ItemsResponse(
    @SerializedName("items")
    val items: List<Item>?,
)
