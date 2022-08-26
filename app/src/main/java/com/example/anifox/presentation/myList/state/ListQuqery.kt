package com.example.anifox.presentation.myList.state

import kotlinx.serialization.Serializable

@Serializable
data class PagerQueryList(
    val listWatchingState: ListWatchingState,
    val listOnHoldState: ListOnHoldState,
    val listAllState: ListAllState,
    val listCompletedState: ListCompletedState
)

@Serializable
data class ListWatchingState(
    val order: String?,
    val status: String?,
    val genre: String?,
    val token: String?
)

@Serializable
data class ListOnHoldState(
    val order: String?,
    val status: String?,
    val genre: String?,
    val token: String?
)

@Serializable
data class ListAllState(
    val order: String?,
    val status: String?,
    val genre: String?,
    val token: String?
)

@Serializable
data class ListCompletedState(
    val order: String?,
    val status: String?,
    val genre: String?,
    val token: String?
)