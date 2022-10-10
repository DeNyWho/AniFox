package com.example.anifox.domain.model.common

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Types(
    val type: String = "",
    val year: String = "",
    val status: String = "",
    val limitation: String = "",
): Parcelable