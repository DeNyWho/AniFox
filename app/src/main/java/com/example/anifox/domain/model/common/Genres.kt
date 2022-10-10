package com.example.anifox.domain.model.common

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Genres(
    val title: List<String> = emptyList(),
): Parcelable