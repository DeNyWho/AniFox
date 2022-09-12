package com.example.anifox.presentation.detail.state.detail

import com.example.anifox.domain.model.manga.Manga

data class ContentDetailsState(
    val data: Manga? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)