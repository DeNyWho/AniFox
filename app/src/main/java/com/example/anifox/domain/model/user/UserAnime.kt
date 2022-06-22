package com.example.anifox.domain.model.user

import com.google.gson.annotations.SerializedName

data class UserAnime(
    @SerializedName("anime")
    val anime: List<UserAnimeStatus>
)
