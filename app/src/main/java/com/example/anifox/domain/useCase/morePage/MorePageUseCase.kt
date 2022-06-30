package com.example.anifox.domain.useCase.morePage

import androidx.paging.PagingSource
import com.example.anifox.data.repository.AnimeRepository
import com.example.anifox.domain.model.anime.Anime
import javax.inject.Inject

class MorePageUseCase @Inject constructor(
    private val repository: AnimeRepository,
) {

    operator fun invoke(order: String?, status: String?): PagingSource<Int, Anime> {
        return repository.getAnimePager(order, status)
    }
}