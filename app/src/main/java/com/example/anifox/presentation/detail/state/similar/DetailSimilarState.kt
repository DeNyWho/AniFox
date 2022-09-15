package com.example.anifox.presentation.detail.state.similar

import com.example.anifox.domain.model.manga.Manga

data class DetailSimilarState(
    val data: List<Manga>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)