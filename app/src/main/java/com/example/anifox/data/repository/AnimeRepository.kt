package com.example.anifox.data.repository

import androidx.paging.PagingSource
import com.example.anifox.data.dataSource.AnimeDataSource
import com.example.anifox.data.remote.api.AnimeApi
import com.example.anifox.domain.model.anime.Anime
import com.example.anifox.domain.repository.RemoteDataSource
import com.example.anifox.util.Constants.ORDER_BY_POPULAR
import com.example.anifox.util.Constants.ORDER_BY_RANKED
import com.example.anifox.util.Constants.REVIEW_LIMIT
import com.example.anifox.util.Constants.REVIEW_PAGE
import com.example.anifox.util.Constants.STATUS_BY_ANONS
import com.example.anifox.util.Constants.STATUS_BY_ONGOING
import retrofit2.Response
import javax.inject.Inject

class AnimeRepository @Inject constructor(
    private val animeApi: AnimeApi,
    private val animeDataSourceFactory: AnimeDataSource.Factory
): RemoteDataSource {

    override fun getAnimePager(order: String?, status: String?): PagingSource<Int, Anime> {
        return animeDataSourceFactory.create(order, status)
    }

    override suspend fun getAnimeByPopularReview(): Response<List<Anime>> {
        return animeApi.getAnimes(page = REVIEW_PAGE, limit = REVIEW_LIMIT, order = ORDER_BY_POPULAR, status = null)
    }

    override suspend fun getDiscoverAnime(): Response<List<Anime>> {
        return animeApi.getAnimes(page = REVIEW_PAGE, limit = REVIEW_LIMIT, order = ORDER_BY_RANKED, status = null)
    }

    override suspend fun getTopAiringReview(): Response<List<Anime>> {
        return animeApi.getAnimes(page = REVIEW_PAGE, limit = REVIEW_LIMIT, order = ORDER_BY_POPULAR, status = STATUS_BY_ONGOING)
    }

    override suspend fun getTopAnnouncesReview(): Response<List<Anime>> {
        return animeApi.getAnimes(page = REVIEW_PAGE, limit = REVIEW_LIMIT, order = ORDER_BY_POPULAR, status = STATUS_BY_ANONS)
    }


}