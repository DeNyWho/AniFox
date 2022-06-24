package com.example.anifox.data.dataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.anifox.data.remote.api.AnimeApi
import com.example.anifox.domain.model.anime.Anime
import com.example.anifox.util.Constants.STARTING_PAGE_INDEX
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class AnimeDataSource @Inject constructor(
    private val animeApi: AnimeApi
) : PagingSource<Int, Anime>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Anime> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = animeApi.getAnimes(page, params.loadSize)
            val anime = response
            Timber.d("ANIME = $anime")
            LoadResult.Page(
                data = anime,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = page + 1
            )

        } catch (exception: IOException) {
            val error = IOException("Please Check Internet Connection")
            LoadResult.Error(error)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Anime>): Int? {
        return state.anchorPosition
    }
}