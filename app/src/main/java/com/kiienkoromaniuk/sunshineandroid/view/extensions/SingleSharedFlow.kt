package com.kiienkoromaniuk.sunshineandroid.view.extensions

import kotlinx.coroutines.flow.MutableSharedFlow

private const val REPLAY_COUNT = 1

@Suppress("FunctionName")
fun <T> SingleSharedFlow() = MutableSharedFlow<T>(REPLAY_COUNT)
