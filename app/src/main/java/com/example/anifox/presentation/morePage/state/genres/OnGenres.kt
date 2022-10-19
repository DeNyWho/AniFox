package com.example.anifox.presentation.morePage.state.genres

import kotlinx.serialization.Serializable

@Serializable
data class OnGenres(
    val data: List<String>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)