package com.example.anifox.presentation.home.state.random

import com.example.anifox.domain.model.manga.Manga

data class RandomState(
    val data: List<Manga>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)