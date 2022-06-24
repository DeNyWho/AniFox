package com.example.anifox.presentation.home.state.animes

import androidx.paging.PagingData
import com.example.anifox.domain.model.anime.Anime
import kotlinx.coroutines.flow.Flow

data class AnimesState(
    val data: Flow<PagingData<Anime>>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)