package com.example.anifox.domain.repository

import androidx.paging.PagingSource
import com.example.anifox.domain.model.manga.Manga
import com.example.anifox.domain.model.responses.MangaResponse
import com.example.anifox.domain.model.responses.PagingFavouriteResponse
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getMangaByIdRu(id: Int): Response<MangaResponse>
    fun getAnimePager(order: String?, status: String?, genre: String?, token: String?): PagingSource<Int, Manga>
    suspend fun getManga( genre: String?, order: String?, status: String?, countCard: Int): Response<MangaResponse>
    suspend fun getSearch(query: String): Response<MangaResponse>
    suspend fun getMangaByUser(token: String, status: String): Response<PagingFavouriteResponse>
}