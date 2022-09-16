package com.example.anifox.presentation.search.state.search

import com.example.anifox.domain.model.manga.Manga

data class SearcherState(
    val data: List<Manga>? = null,
    val isLoading: Boolean = false,
    val message: String? = null,
    val error: String? = null
)