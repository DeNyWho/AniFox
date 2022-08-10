package com.example.anifox.presentation.home.state.discover

import com.example.anifox.domain.model.manga.Manga

data class DiscoverState(
    val data: List<Manga>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
