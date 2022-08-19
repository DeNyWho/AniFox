package com.example.anifox.data.repository

import androidx.paging.PagingSource
import com.example.anifox.data.dataSource.AnimeDataSource
import com.example.anifox.data.remote.api.MainApi
import com.example.anifox.domain.model.manga.Manga
import com.example.anifox.domain.model.responses.MangaResponse
import com.example.anifox.domain.repository.RemoteDataSource
import com.example.anifox.util.Constants.REVIEW_LIMIT
import com.example.anifox.util.Constants.REVIEW_PAGE
import com.example.anifox.util.Constants.SORT_BY_RATE
import com.example.anifox.util.Constants.ORDER_BY_VIEWS
import com.example.anifox.util.Constants.STATUS_BY_FINAL
import com.example.anifox.util.Constants.STATUS_BY_ONGOING
import retrofit2.Response
import javax.inject.Inject

class MangaRepository @Inject constructor(
    private val mainApi: MainApi,
    private val animeDataSourceFactory: AnimeDataSource.Factory
): RemoteDataSource {

    override fun getAnimePager(order: String?, status: String?, genre: String?): PagingSource<Int, Manga> {
        return animeDataSourceFactory.create(order, status, genre)
    }

    override suspend fun getMangaByIdRu(id: Int): Response<MangaResponse> {
        return mainApi.getDetailManga(id)
    }

    override suspend fun getManga(genre: String?, order: String?, status: String?): Response<MangaResponse>{
        return mainApi.getManga(page = REVIEW_PAGE, countCard = REVIEW_LIMIT, status = status, genre = genre, order = order)
    }

    override suspend fun getMangaByPopularReview(): Response<MangaResponse> {
        return mainApi.getManga(page = REVIEW_PAGE, countCard = REVIEW_LIMIT, status = null, order = SORT_BY_RATE, genre = null)
    }

    override suspend fun getDiscoverAnime(): Response<MangaResponse> {
        return mainApi.getManga(page = REVIEW_PAGE, countCard = REVIEW_LIMIT, status = STATUS_BY_FINAL, order = SORT_BY_RATE,  genre = null)
    }

    override suspend fun getMostReadManga(): Response<MangaResponse> {
        return mainApi.getManga(page = REVIEW_PAGE, countCard = REVIEW_LIMIT, status = null, order = ORDER_BY_VIEWS,  genre = null)
    }

    override suspend fun getTopAiringReview(): Response<MangaResponse> {
        return mainApi.getManga(page = REVIEW_PAGE, countCard = REVIEW_LIMIT, status = STATUS_BY_ONGOING, order = SORT_BY_RATE,  genre = null)
    }



}