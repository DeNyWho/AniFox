package com.example.anifox.presentation.morePage.state.onGoing

import kotlinx.serialization.Serializable

@Serializable
data class OnGoingQuery(
    val order: String?,
    val status: String?,
    val genre: String?
)