package com.example.anifox.presentation.home.state.manga.middleAges

import com.example.anifox.domain.model.manga.Manga

data class MiddleAgesState(
    val data: List<Manga>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
