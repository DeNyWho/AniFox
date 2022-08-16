package com.example.anifox.presentation.home.state.manga.magic

import com.example.anifox.domain.model.manga.Manga

data class MagicState(
    val data: List<Manga>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
