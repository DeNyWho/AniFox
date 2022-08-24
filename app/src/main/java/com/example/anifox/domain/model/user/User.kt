package com.example.anifox.domain.model.user

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerializedName("id")
    val id: Long,
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("enabled")
    val enabled: Boolean,
    @SerializedName("token")
    val token: String,
    @SerializedName("roles")
    val roles: List<Roles>
)

fun User.toData(): User {
    return User(
        id = id,
        username = username,
        email = email,
        password = password,
        enabled = enabled,
        token = token,
        roles = roles,
    )
}

