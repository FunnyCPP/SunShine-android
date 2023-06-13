package com.kiienkoromaniuk.sunshineandroid.data.model

import com.google.gson.annotations.SerializedName

enum class StocktakingItemType {
    @SerializedName("required_item")
    REQUIRED_ITEM,

    @SerializedName("missing_item")
    MISSING_ITEM,

    @SerializedName("additional_item")
    ADDITIONAL_ITEM,
}
