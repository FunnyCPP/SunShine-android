package com.kiienkoromaniuk.sunshineandroid.ui.stocktaking.state

import com.kiienkoromaniuk.sunshineandroid.data.model.Item

data class StocktakingState(
    val requiredItems: List<Item> = emptyList(),
    val addedItems: List<Item> = emptyList(),
    val remainingItems: List<Item> = emptyList(),
    val additionalItems: List<Item> = emptyList(),
    val lastItemCode: String? = null,
)
