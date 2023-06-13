package com.kiienkoromaniuk.sunshineandroid.view.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController

@Composable
fun <T> NavController.GetOnceResult(keyResult: String, onResult: (T) -> Unit){
    val valueScreenResult =  currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<T>(keyResult)?.observeAsState()

    valueScreenResult?.value?.let {
        onResult(it)

        currentBackStackEntry
            ?.savedStateHandle
            ?.remove<T>(keyResult)
    }
}

fun <T> NavController.getOnceResult(keyResult: String, onResult: (T) -> Unit){
    val valueScreenResult =  currentBackStackEntry
        ?.savedStateHandle?.get<T>(keyResult)

    valueScreenResult?.let {
        onResult(it)

        currentBackStackEntry
            ?.savedStateHandle
            ?.remove<T>(keyResult)
    }
}