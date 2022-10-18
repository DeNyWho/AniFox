package com.example.anifox.presentation.morePage.state.onPopular

import kotlinx.serialization.Serializable

@Serializable
data class OnPopularQuery(
    val order: String?,
    val status: String?,
    val genre: String?
)