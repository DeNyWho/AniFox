package com.example.anifox.domain.model.common

import kotlinx.serialization.Serializable

@Serializable
data class PagerQuery(
    val order: String?,
    val status: String?
)