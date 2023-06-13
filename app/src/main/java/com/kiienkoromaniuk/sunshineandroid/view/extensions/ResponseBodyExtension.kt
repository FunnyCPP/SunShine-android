package com.kiienkoromaniuk.sunshineandroid.view.extensions

import com.google.gson.GsonBuilder
import com.kiienkoromaniuk.sunshineandroid.data.ErrorResponse
import okhttp3.ResponseBody

fun ResponseBody.toErrorResponse(): ErrorResponse? {
    return try {
        GsonBuilder().create().fromJson(string(), ErrorResponse::class.java)
    } catch (exception: Exception) {
        null
    }
}
