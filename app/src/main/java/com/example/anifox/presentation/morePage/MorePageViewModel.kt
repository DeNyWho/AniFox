package com.example.anifox.presentation.morePage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.anifox.domain.model.manga.Manga
import com.example.anifox.domain.useCase.morePage.MorePageUseCase
import com.example.anifox.presentation.morePage.state.PagerQueryState
import com.example.anifox.presentation.morePage.state.onCompleted.OnCompletedQuery
import com.example.anifox.presentation.morePage.state.onGoing.OnGoingQuery
import com.example.anifox.presentation.morePage.state.onPopular.OnPopularQuery
import com.example.anifox.util.Constants
import com.example.anifox.util.Constants.ORDER_BY_POPULAR
import com.example.anifox.util.Constants.STATUS_BY_FINAL
import com.example.anifox.util.Constants.STATUS_BY_ONGOING
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MorePageViewModel @Inject constructor(
    private val getMorePage: MorePageUseCase,
) : ViewModel() {

    private val _queries = MutableStateFlow(PagerQueryState(
            onGoingQuery = OnGoingQuery(ORDER_BY_POPULAR, STATUS_BY_ONGOING, null),
            onPopularQuery = OnPopularQuery(ORDER_BY_POPULAR, null, null),
            onCompletedQuery = OnCompletedQuery(ORDER_BY_POPULAR, STATUS_BY_FINAL, null,)
        )
    )

    private var newPagingSource: PagingSource<*, *>? = null


    fun setQueriesOnGoing(genre: String?) {
        _queries.tryEmit(
            _queries.value.copy(
                onGoingQuery = _queries.value.onGoingQuery.copy(
                    genre = genre
                )
            )
        )
    }

    fun setQueriesOnPopular(genre: String?) {
        _queries.tryEmit(
            _queries.value.copy(
                onPopularQuery = _queries.value.onPopularQuery.copy(
                    genre = genre,
                )
            )
        )
    }

    fun setQueriesOnCompleted( genre: String?) {
        _queries.tryEmit(
            _queries.value.copy(
                onCompletedQuery = _queries.value.onCompletedQuery.copy(
                    genre = genre
                )
            )
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getMangasOnGoingPaging(): StateFlow<PagingData<Manga>> {
        return _queries
            .map(::newPagerOnGoing)
            .flatMapLatest { pager -> pager.flow }
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getMangasOnPopularPaging(): StateFlow<PagingData<Manga>> {
        return _queries
            .map(::newPagerOnPopular)
            .flatMapLatest { pager -> pager.flow }
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getMangasOnCompletedPaging(): StateFlow<PagingData<Manga>> {
        return  _queries
            .map(::newPagerOnCompleted)
            .flatMapLatest { pager -> pager.flow }
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
    }


    private fun newPagerOnGoing(queries: PagerQueryState): Pager<Int, Manga> {
        return Pager(PagingConfig(Constants.MORE_PAGE_SIZE, enablePlaceholders = false)) {
            getMorePage.invoke(
                order = queries.onGoingQuery.order,
                status = queries.onGoingQuery.status,
                genre =  queries.onGoingQuery.genre,
                token = null
            ).also { newPagingSource = it }
        }
    }

    private fun newPagerOnPopular(queries: PagerQueryState): Pager<Int, Manga> {
        return Pager(PagingConfig(Constants.MORE_PAGE_SIZE, enablePlaceholders = false)) {
            getMorePage.invoke(
                order = queries.onPopularQuery.order,
                status = queries.onPopularQuery.status,
                genre =  queries.onPopularQuery.genre,
                token = null
            ).also { newPagingSource = it }
        }
    }

    private fun newPagerOnCompleted(queries: PagerQueryState): Pager<Int, Manga> {
        return Pager(PagingConfig(Constants.MORE_PAGE_SIZE, enablePlaceholders = false)) {
            getMorePage.invoke(
                order = queries.onCompletedQuery.order,
                status = queries.onCompletedQuery.status,
                genre =  queries.onCompletedQuery.genre,
                token = null
            ).also { newPagingSource = it }
        }
    }

}