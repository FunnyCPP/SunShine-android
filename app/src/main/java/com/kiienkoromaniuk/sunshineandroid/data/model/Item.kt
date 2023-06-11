package com.kiienkoromaniuk.sunshineandroid.data.model

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("room")
    val room: String?,
    @SerializedName("purchasing_date")
    val purchasingDate: String?,
    @SerializedName("scrapping_date")
    val scrappingDate: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("house")
    val house: String?,
    @SerializedName("code")
    val code: String?,
)
