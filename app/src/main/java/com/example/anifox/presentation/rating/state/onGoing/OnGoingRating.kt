package com.example.anifox.presentation.rating.state.onGoing

import kotlinx.serialization.Serializable

@Serializable
data class OnGoingRating(
    val order: String?,
    val status: String?,
    val genre: String?
)