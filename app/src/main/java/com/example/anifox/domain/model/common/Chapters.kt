package com.example.anifox.domain.model.common

import kotlinx.serialization.Serializable

@Serializable
data class Chapters(
    val title: List<String>,
    val url: List<String>,
)
