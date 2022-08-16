package com.example.anifox.domain.repository

import androidx.paging.PagingSource
import com.example.anifox.domain.model.manga.Manga
import com.example.anifox.domain.model.responses.MangaResponse
import retrofit2.Response

interface RemoteDataSource {
//    fun getAnimePager(order: String?, status: String?): PagingSource<Int, Manga>
    suspend fun getMangaByPopularReview(): Response<MangaResponse>
    suspend fun getDiscoverAnime(): Response<MangaResponse>
    suspend fun getTopAiringReview(): Response<MangaResponse>
    suspend fun getMostReadManga(): Response<MangaResponse>
    suspend fun getMangaByIdRu(id: Int): Response<MangaResponse>
    fun getAnimePager(order: String?, status: String?, genre: String?): PagingSource<Int, Manga>
    suspend fun getManga(genre: String?, order: String?): Response<MangaResponse>
}