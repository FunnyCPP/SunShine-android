package com.kiienkoromaniuk.sunshineandroid.data.response

import com.google.gson.annotations.SerializedName

data class BootstrapResponse(
    @SerializedName("items_count")
    val itemsCount: Long?,
    @SerializedName("stocktaking_count")
    val stocktakingCount: Long?,
)
