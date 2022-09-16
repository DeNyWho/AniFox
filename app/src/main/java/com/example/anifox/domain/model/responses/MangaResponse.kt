package com.example.anifox.domain.model.responses

import com.example.anifox.domain.model.manga.Manga
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MangaResponse(
    @SerialName("data")
    val `data`: List<Manga> = listOf(),
    @SerialName("message")
    val message: String? = null
)
