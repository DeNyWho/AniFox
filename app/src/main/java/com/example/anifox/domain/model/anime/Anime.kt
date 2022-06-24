package com.example.anifox.domain.model.anime

import com.example.anifox.domain.model.common.ShikimoriImage
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Anime(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("russian") val russian: String,
    @SerializedName("image") val image: ShikimoriImage,
    @SerializedName("url") val url: String,
    @SerializedName("kind") val kind: String,
    @SerializedName("score") val score: String,
    @SerializedName("status") val released: String,
    @SerializedName("episodes") val episodes: String,
    @SerializedName("episodes_aired") val episodes_aired: String,
    @SerializedName("aired_on") val aired_on: String,
)

fun Anime.toPopular(): Anime {
    return Anime(
        id = id,
        name = name,
        russian = russian,
        image = image,
        url = url,
        kind = kind,
        score = score,
        released = released,
        episodes = episodes,
        episodes_aired = episodes_aired,
        aired_on = aired_on
    )
}
