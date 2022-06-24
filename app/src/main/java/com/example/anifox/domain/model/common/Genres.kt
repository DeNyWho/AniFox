package com.example.anifox.domain.model.common

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Genres(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("russian") val russian: String,
    @SerializedName("kind") val kind: String
): Parcelable
