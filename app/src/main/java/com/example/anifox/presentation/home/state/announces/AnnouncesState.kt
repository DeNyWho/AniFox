package com.example.anifox.presentation.home.state.announces

import com.example.anifox.domain.model.anime.Anime

data class AnnouncesState(
    val data: List<Anime>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)