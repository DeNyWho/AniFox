package com.example.anifox.domain.useCase.home

import androidx.paging.PagingData
import com.example.anifox.data.repository.AnimeRepository
import com.example.anifox.domain.model.anime.Anime
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAnimesUseCase @Inject constructor(
    private val repository: AnimeRepository,
) {
    operator fun invoke(): Flow<PagingData<Anime>> {
        return repository.getAllAnimes()
    }
}