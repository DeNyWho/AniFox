package com.example.anifox.domain.model.manga

import com.example.anifox.domain.model.common.Chapters
import com.example.anifox.domain.model.common.Genres
import com.example.anifox.domain.model.common.Types
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
    @SerializedName("description")
    val description: String = "",
    @SerializedName("genres")
    val genres: Genres = Genres(),
    @SerializedName("chapters")
    val chapters: Chapters = Chapters(),
    @SerializedName("types")
    val types: Types = Types(),
    @SerializedName("chaptersCount")
    val chaptersCount: Int = 0,
    @SerializedName("countViews")
    val views: String = "",
    @SerializedName("rate")
    val rate: String = "",
    @SerializedName("countRate")
    val countRate: String = "",
)

fun Manga.toData(): Manga {
    return Manga(
        id = id,
        title = title,
        image = image,
        url = url,
        rate = rate,
        countRate = countRate,
        description = description,
        views = views
    )
}

fun Manga.toDataFull(): Manga {
    return Manga(
        id = id,
        title = title,
        image = image,
        types = types,
        genres = genres,
        chaptersCount = chaptersCount,
        chapters = chapters,
        url = url,
        rate = rate,
        countRate = countRate,
        description = description,
        views = views
    )
}
