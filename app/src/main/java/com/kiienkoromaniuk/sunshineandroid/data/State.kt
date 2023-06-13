package com.kiienkoromaniuk.sunshineandroid.data

sealed interface State<R> {
    class Progress<R> : State<R>

    data class Success<R>(
        val response: R?,
    ) : State<R>, StateWithoutProgress<R>

    data class Error<R>(
        val httpCode: Int,
        val error: ErrorResponse?,
        val exception: Exception? = null,
    ) : State<R>, StateWithoutProgress<R>
}

sealed interface StateWithoutProgress<R>
