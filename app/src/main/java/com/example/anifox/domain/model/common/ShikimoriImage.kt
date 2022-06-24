package com.example.anifox.domain.model.common

import kotlinx.serialization.Serializable

@Serializable
data class ShikimoriImage(
    val original: String?,
    val preview: String?,
)