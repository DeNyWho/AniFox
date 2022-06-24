package com.example.anifox.domain.repository

import androidx.paging.PagingData
import com.example.anifox.domain.model.anime.Anime
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RemoteDataSource {
    fun getAllAnimes(): Flow<PagingData<Anime>>
    suspend fun getAnimeByPopularReview(): Response<List<Anime>>
}