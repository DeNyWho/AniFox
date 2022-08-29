package com.example.anifox.domain.model.common

import kotlinx.serialization.Serializable

@Serializable
data class Types(
    val type: String = "",
    val year: String = "",
    val status: String = "",
    val limitation: String = "",
)