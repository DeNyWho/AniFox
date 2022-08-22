package com.example.anifox.presentation.search.state.status

import com.example.anifox.domain.model.manga.Manga

data class OnFinalSearchState(
    val data: List<Manga>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
