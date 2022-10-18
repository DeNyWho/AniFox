package com.example.anifox.presentation.rating.state.onCompleted

import kotlinx.serialization.Serializable

@Serializable
data class OnCompletedRating(
    val order: String?,
    val status: String?,
    val genre: String?
)