package com.example.anifox.domain.model.user

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class UserSignIn (
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String
)