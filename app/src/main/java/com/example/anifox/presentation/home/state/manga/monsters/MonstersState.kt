package com.example.anifox.presentation.home.state.manga.monsters

import com.example.anifox.domain.model.manga.Manga

data class MonstersState(
    val data: List<Manga>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
