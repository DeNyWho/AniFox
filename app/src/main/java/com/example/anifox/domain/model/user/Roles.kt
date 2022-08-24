package com.example.anifox.domain.model.user

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Roles(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String
)