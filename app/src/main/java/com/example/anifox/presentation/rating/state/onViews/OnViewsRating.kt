package com.example.anifox.presentation.rating.state.onViews

import kotlinx.serialization.Serializable

@Serializable
data class OnViewsRating(
    val order: String?,
    val status: String?,
    val genre: String?
)