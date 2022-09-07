package com.example.anifox.domain.model.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BasicResponse(
    @SerialName("data")
    val `data`: List<String> = listOf(),
    @SerialName("message")
    val message: String = ""
)