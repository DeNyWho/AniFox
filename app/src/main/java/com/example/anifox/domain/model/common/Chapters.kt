package com.example.anifox.domain.model.common

import kotlinx.serialization.Serializable

@Serializable
data class Chapters(
    val title: List<String> = emptyList(),
    val date: List<String> = emptyList(),
    val url: List<String> = emptyList(),
)
