package com.example.anifox.domain.model.common

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Screenshots(
    @SerializedName("original") val original: String,
    @SerializedName("preview") val preview: String,
): Parcelable
