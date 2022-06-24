package com.example.anifox.domain.model.common

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Videos(
    @SerializedName("id") val id: Int,
    @SerializedName("url") val url: String,
    @SerializedName("image_url") val image_url: String,
    @SerializedName("player_url") val player_url: String,
    @SerializedName("name") val name: String,
    @SerializedName("kind") val kind: String,
    @SerializedName("hosting") val hosting: String,
): Parcelable
