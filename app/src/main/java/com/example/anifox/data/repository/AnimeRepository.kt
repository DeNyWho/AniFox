package com.example.anifox.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.anifox.data.dataSource.AnimeDataSource
import com.example.anifox.data.remote.api.AnimeApi
import com.example.anifox.domain.model.anime.Anime
import com.example.anifox.domain.repository.RemoteDataSource
import com.example.anifox.util.Constants
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimeRepository @Inject constructor(
    private val animeApi: AnimeApi,
): RemoteDataSource {
    override fun getAllAnimes(): Flow<PagingData<Anime>> {
        return Pager(
            config = PagingConfig(
                enablePlaceholders = false,
                pageSize = Constants.HOME_PAGE_SIZE,
            ),
            pagingSourceFactory = {
                AnimeDataSource(animeApi)
            }
        ).flow
    }

    override suspend fun getAnimeByPopularReview(): Response<List<Anime>> {
        return animeApi.getAnimesPreview(page = 1, limit = 10, order = "popularity")
    }


}