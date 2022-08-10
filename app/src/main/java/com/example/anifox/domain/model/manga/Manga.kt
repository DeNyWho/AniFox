package com.example.anifox.domain.model.manga

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Manga(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("image")
    val image: String = "",
    @SerializedName("url")
    val url: String = "",
)

fun Manga.toData(): Manga {
    return Manga(
        id = id,
        title = title,
        image = image,
        url = url,
    )
}
