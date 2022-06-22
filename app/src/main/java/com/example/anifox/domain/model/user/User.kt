package com.example.anifox.domain.model.user

import com.example.anifox.domain.model.common.ProfileImage
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") val id: Int,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("image") val images: ProfileImage,
    @SerializedName("stats") val stats: UserStats,
    @SerializedName("common_info") val commonInfo: List<String>
)
