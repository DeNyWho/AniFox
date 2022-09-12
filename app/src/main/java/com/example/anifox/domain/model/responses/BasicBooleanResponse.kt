package com.example.anifox.domain.model.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BasicBooleanResponse(
    @SerialName("data")
    val `data`: List<Boolean> = listOf(),
    @SerialName("message")
    val message: String = ""
)