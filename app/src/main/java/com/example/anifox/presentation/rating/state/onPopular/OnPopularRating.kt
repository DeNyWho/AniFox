package com.example.anifox.presentation.rating.state.onPopular

import kotlinx.serialization.Serializable

@Serializable
data class OnPopularRating(
    val order: String?,
    val status: String?,
    val genre: String?
)