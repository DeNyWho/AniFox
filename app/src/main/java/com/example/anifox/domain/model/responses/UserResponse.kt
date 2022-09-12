package com.example.anifox.domain.model.responses

import com.example.anifox.domain.model.user.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("data")
    val `data`: List<User> = listOf(),
    @SerialName("error")
    val error: String? = null,
    @SerialName("message")
    val message: String? = null,
)
