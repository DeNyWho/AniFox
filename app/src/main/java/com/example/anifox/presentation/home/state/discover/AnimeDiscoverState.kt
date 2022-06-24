package com.example.anifox.presentation.home.state.discover

import com.example.anifox.domain.model.anime.Anime

data class AnimeDiscoverState(
    val data: List<Anime>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)