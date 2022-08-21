package com.example.anifox.domain.useCase.morePage

import androidx.paging.PagingSource
import com.example.anifox.data.repository.MangaRepository
import com.example.anifox.domain.model.manga.Manga
import javax.inject.Inject

class MorePageUseCase @Inject constructor(
    private val repository: MangaRepository,
) {
    operator fun invoke(order: String?, status: String?, genre: String?): PagingSource<Int, Manga> {
        return repository.getAnimePager(order, status, genre)
    }
}