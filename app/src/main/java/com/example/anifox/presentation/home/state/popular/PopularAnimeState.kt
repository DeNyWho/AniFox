package com.example.anifox.presentation.home.state.popular

import com.example.anifox.domain.model.anime.Anime

data class PopularAnimeState(
    val data: List<Anime>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)