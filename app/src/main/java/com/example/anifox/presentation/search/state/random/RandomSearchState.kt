package com.example.anifox.presentation.search.state.random

import com.example.anifox.domain.model.manga.Manga

data class RandomSearchState(
    val data: List<Manga>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

