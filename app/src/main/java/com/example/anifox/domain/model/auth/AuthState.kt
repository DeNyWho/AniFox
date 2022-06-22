package com.example.anifox.domain.model.auth

data class AuthState(
    val token: String? = null,
    val refreshToken: String? = null,
    var message: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)