package com.example.anifox.data.dataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.anifox.data.remote.api.MangaApi
import com.example.anifox.domain.model.manga.Manga
import com.example.anifox.domain.model.manga.toData
import com.example.anifox.util.Constants.MORE_PAGE_SIZE
import com.example.anifox.util.Constants.STARTING_PAGE_INDEX
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

class AnimeDataSource @AssistedInject constructor(
    private val mangaApi: MangaApi,
    @Assisted("order")
    private val order: String?,
    @Assisted("status")
    private val status: String?,
    @Assisted("genre")
    private val genre: String?,
    @Assisted("token")
    private val token: String?,

    ) : PagingSource<Int, Manga>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Manga> {
        val page = params.key ?: STARTING_PAGE_INDEX
        val pageSize = params.loadSize.coerceAtMost(MORE_PAGE_SIZE)
        return try {

            println("page = $page, loadSize = ${pageSize}, order = $order, status = $status, genre = $genre, token = $token")

            val response = if(token == null){ mangaApi.getManga(
                page = page,
                countCard = pageSize,
                order = order,
                status = status,
                genre = genre
            ).body()?.data?.map { it.toData() }.orEmpty()} else {
                mangaApi.getMangaByUser(
                    pageNum = page + 1,
                    pageSize = pageSize,
                    status = status,
                    token = token
                ).body()?.data?.data?.map { it.toData() }.orEmpty()
            }

            Timber.d("$response")
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

    override fun getRefreshKey(state: PagingState<Int, Manga>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("order") order: String?,
            @Assisted("status") status: String?,
            @Assisted("genre") genre: String?,
            @Assisted("token") token: String?,
        ): AnimeDataSource
    }
}