package com.example.anifox.presentation.home.state.popular

import com.example.anifox.domain.model.anime.Anime

data class AiringPopularAnimeState(
    val data: List<Anime>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)