package com.example.anifox.domain.model.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PagingFavouriteResponse(
    @SerialName("data")
    val `data`: DataResponse = DataResponse()
)
