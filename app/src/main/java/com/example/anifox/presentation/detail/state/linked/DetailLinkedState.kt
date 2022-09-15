package com.example.anifox.presentation.detail.state.linked

import com.example.anifox.domain.model.manga.Manga

data class DetailLinkedState(
    val data: List<Manga>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)