package com.example.anifox.data.repository

import androidx.paging.PagingSource
import com.example.anifox.data.dataSource.AnimeDataSource
import com.example.anifox.data.remote.api.MangaApi
import com.example.anifox.domain.model.manga.Manga
import com.example.anifox.domain.model.manga.NewFavouriteManga
import com.example.anifox.domain.model.responses.BasicBooleanResponse
import com.example.anifox.domain.model.responses.MangaResponse
import com.example.anifox.domain.model.responses.PagingFavouriteResponse
import com.example.anifox.domain.repository.RemoteDataSource
import com.example.anifox.util.Constants.FAVOURITE_CARDS
import com.example.anifox.util.Constants.FAVOURITE_PAGE
import com.example.anifox.util.Constants.REVIEW_PAGE
import retrofit2.Response
import javax.inject.Inject

class MangaRepository @Inject constructor(
    private val mangaApi: MangaApi,
    private val animeDataSourceFactory: AnimeDataSource.Factory
): RemoteDataSource {

    override fun getAnimePager(order: String?, status: String?, genre: String?, token: String?): PagingSource<Int, Manga> {
        return animeDataSourceFactory.create(order, status, genre, token)
    }

    override suspend fun getMangaByIdRu(id: Int): Response<MangaResponse> {
        return mangaApi.getDetailManga(id)
    }

    override suspend fun getSimilarManga(id: Int): Response<MangaResponse> {
        return mangaApi.getSimilarManga(id)
    }

    override suspend fun getLinkedManga(id: Int): Response<MangaResponse> {
        return mangaApi.getLinkedManga(id)
    }

    override suspend fun getManga(genre: String?, order: String?, status: String?, countCard: Int): Response<MangaResponse>{
        return mangaApi.getManga(page = REVIEW_PAGE, countCard = countCard, status = status, genre = genre, order = order)
    }

    override suspend fun getSearch(query: String): Response<MangaResponse>{
        return mangaApi.getSearchManga(query)
    }

    override suspend fun getMangaByUser(token: String, status: String): Response<PagingFavouriteResponse>{
        return mangaApi.getMangaByUser(token = token, status = status, pageNum = FAVOURITE_PAGE, pageSize = FAVOURITE_CARDS)
    }

    override suspend fun addFavouriteManga(newFavourite: NewFavouriteManga, status: String): Response<BasicBooleanResponse>{
        return mangaApi.newFavourite(status = status, newFavourite)
    }



}