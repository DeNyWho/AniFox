package com.example.anifox.presentation.morePage.state

import com.example.anifox.presentation.morePage.state.genres.OnGenres
import com.example.anifox.presentation.morePage.state.onCompleted.OnCompletedQuery
import com.example.anifox.presentation.morePage.state.onGoing.OnGoingQuery
import com.example.anifox.presentation.morePage.state.onPopular.OnPopularQuery
import kotlinx.serialization.Serializable

@Serializable
data class MorePagerState(
    val onGoingQuery: OnGoingQuery,
    val onPopularQuery: OnPopularQuery,
    val onCompletedQuery: OnCompletedQuery,
    val onGenres: OnGenres
)
