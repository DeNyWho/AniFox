package com.example.anifox.presentation.home.state.mostRead

import com.example.anifox.domain.model.manga.Manga

data class MostReadState(
    val data: List<Manga>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)