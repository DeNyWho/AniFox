package com.example.anifox.presentation.rating.state

import com.example.anifox.presentation.rating.state.onCompleted.OnCompletedRating
import com.example.anifox.presentation.rating.state.onGoing.OnGoingRating
import com.example.anifox.presentation.rating.state.onPopular.OnPopularRating
import com.example.anifox.presentation.rating.state.onViews.OnViewsRating
import kotlinx.serialization.Serializable

@Serializable
data class RatingQueryState(
    val onGoingQuery: OnGoingRating,
    val onPopularQuery: OnPopularRating,
    val onCompletedQuery: OnCompletedRating,
    val onViewsQuery: OnViewsRating
)