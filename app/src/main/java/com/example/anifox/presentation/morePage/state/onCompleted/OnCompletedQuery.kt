package com.example.anifox.presentation.morePage.state.onCompleted

import kotlinx.serialization.Serializable

@Serializable
data class OnCompletedQuery(
    val order: String?,
    val status: String?,
    val genre: String?
)