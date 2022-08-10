package com.example.anifox.presentation.home.state.popular

import com.example.anifox.domain.model.manga.Manga

data class AiringPopularAnimeState(
    val data: List<Manga>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)