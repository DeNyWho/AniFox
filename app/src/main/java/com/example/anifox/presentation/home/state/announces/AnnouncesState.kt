package com.example.anifox.presentation.home.state.announces

import com.example.anifox.domain.model.manga.Manga

data class AnnouncesState(
    val data: List<Manga>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)