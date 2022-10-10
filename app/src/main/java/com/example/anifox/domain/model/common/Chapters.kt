package com.example.anifox.domain.model.common

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Chapters(
    val title: List<String> = emptyList(),
    val date: List<String> = emptyList(),
    val url: List<String> = emptyList(),
): Parcelable
