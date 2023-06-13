package com.kiienkoromaniuk.sunshineandroid.data

data class ErrorResponse(
    val exception: String = "",
    val message: String = "",
    val hint: String = "",
)
