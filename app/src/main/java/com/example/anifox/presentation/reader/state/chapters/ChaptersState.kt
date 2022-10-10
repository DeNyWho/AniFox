package com.example.anifox.presentation.reader.state.chapters

import com.example.anifox.domain.model.common.Chapters

data class ChaptersState(
    val data: Chapters? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

