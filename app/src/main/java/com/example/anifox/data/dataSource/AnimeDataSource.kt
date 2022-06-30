package com.example.anifox.data.dataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.anifox.data.remote.api.AnimeApi
import com.example.anifox.domain.model.anime.Anime
import com.example.anifox.domain.model.anime.toData
import com.example.anifox.util.Constants.MORE_PAGE_SIZE
import com.example.anifox.util.Constants.STARTING_PAGE_INDEX
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

class AnimeDataSource @AssistedInject constructor(
    private val animeApi: AnimeApi,
    @Assisted("order")
    private val order: String?,
    @Assisted("status")
    private val status: String?
) : PagingSource<Int, Anime>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Anime> {
        val page = params.key ?: STARTING_PAGE_INDEX
        val pageSize = params.loadSize.coerceAtMost(MORE_PAGE_SIZE)
        return try {

            Timber.d("page = $page, loadSize = ${pageSize}, order = $order, status = $status")

            val response: List<Anime> = animeApi.getAnimes(page, pageSize, order, status).body()?.map { it.toData() }.orEmpty()
            val temp = animeApi.getAnimes(page, pageSize, order, status).headers()
            Timber.d("DATA_PAGER = $temp")
            LoadResult.Page(
                data = response,
                prevKey = if (page > 1) page - 1 else null,
                nextKey = if(response.isEmpty()) null else  page + 1
            )

        } catch (exception: IOException) {
            val error = IOException("Please Check Internet Connection")
            LoadResult.Error(error)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Anime>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    @AssistedFactory
    interface Factory {

        fun create(@Assisted("order") order: String?, @Assisted("status") status: String?): AnimeDataSource
    }
}