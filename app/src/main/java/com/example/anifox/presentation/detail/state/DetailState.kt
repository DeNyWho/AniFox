package com.example.anifox.presentation.detail.state

import com.example.anifox.presentation.detail.state.detail.ContentDetailsState
import com.example.anifox.presentation.detail.state.favourite.DetailFavouriteState
import com.example.anifox.presentation.detail.state.linked.DetailLinkedState
import com.example.anifox.presentation.detail.state.similar.DetailSimilarState
import com.example.anifox.presentation.detail.state.token.DetailTokenState

data class DetailState (
    val contentDetailsState: ContentDetailsState,
    val detailLinkedState: DetailLinkedState,
    val detailSimilarState: DetailSimilarState,
    val detailFavouriteState: DetailFavouriteState,
    val detailTokenState: DetailTokenState
)