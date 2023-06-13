package com.kiienkoromaniuk.sunshineandroid.data.request

data class RegisterRequest(
    val firstname: String,
    val lastname: String,
    val email: String,
    val password: String,
    val role: String = "ADMIN",
)
