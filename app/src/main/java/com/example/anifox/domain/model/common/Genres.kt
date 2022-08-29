package com.example.anifox.domain.model.common

import kotlinx.serialization.Serializable

@Serializable
data class Genres(
    val title: List<String> = emptyList(),
)