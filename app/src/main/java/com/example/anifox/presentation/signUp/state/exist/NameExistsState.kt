package com.example.anifox.presentation.signUp.state.exist

data class NameExistsState(
    val data: List<Boolean>? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val message: String? = null,
)