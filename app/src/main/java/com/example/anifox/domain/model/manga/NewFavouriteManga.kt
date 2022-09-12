package com.example.anifox.domain.model.manga

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class NewFavouriteManga (
    @SerializedName("token")
    val token: String,
    @SerializedName("mangaId")
    val mangaId: Int
)