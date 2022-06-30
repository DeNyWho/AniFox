package com.example.anifox.domain.repository

import androidx.paging.PagingSource
import com.example.anifox.domain.model.anime.Anime
import retrofit2.Response

interface RemoteDataSource {
    fun getAnimePager(order: String?, status: String?): PagingSource<Int, Anime>
    suspend fun getAnimeByPopularReview(): Response<List<Anime>>
    suspend fun getDiscoverAnime(): Response<List<Anime>>
    suspend fun getTopAiringReview(): Response<List<Anime>>
    suspend fun getTopAnnouncesReview(): Response<List<Anime>>
}