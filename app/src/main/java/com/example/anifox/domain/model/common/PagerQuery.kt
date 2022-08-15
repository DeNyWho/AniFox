package com.example.anifox.domain.model.common

import kotlinx.serialization.Serializable

@Serializable
data class PagerQuery(
    val OnGoingQuery: OnGoingQuery,
    val OnPopularQuery: OnPopularQuery,
    val OnCompletedQuery: OnCompletedQuery
)

@Serializable
data class OnGoingQuery(
    val order: String?,
    val status: String?,
    val genre: String?
)

@Serializable
data class OnPopularQuery(
    val order: String?,
    val status: String?,
    val genre: String?
)

@Serializable
data class OnCompletedQuery(
    val order: String?,
    val status: String?,
    val genre: String?
)
