package com.example.anifox.domain.model.user

import com.google.gson.annotations.SerializedName

data class ActivityItem(
    @SerializedName("name") val date: List<Long>,
    @SerializedName("value") val value: Int
)