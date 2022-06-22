package com.example.anifox.domain.model.user

import com.google.gson.annotations.SerializedName

data class UserStats(
    @SerializedName("statuses") val statuses: UserAnime,
    @SerializedName("activity") val activity: List<ActivityItem>
)
