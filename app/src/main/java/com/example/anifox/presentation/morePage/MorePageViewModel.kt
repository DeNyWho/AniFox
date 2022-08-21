package com.example.anifox.presentation.morePage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.anifox.domain.model.common.OnCompletedQuery
import com.example.anifox.domain.model.common.OnGoingQuery
import com.example.anifox.domain.model.common.OnPopularQuery
import com.example.anifox.domain.model.common.PagerQuery
import com.example.anifox.domain.model.manga.Manga
import com.example.anifox.domain.useCase.morePage.MorePageUseCase
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

    private val _queries = MutableStateFlow(PagerQuery(
            OnGoingQuery = OnGoingQuery(ORDER_BY_POPULAR, STATUS_BY_ONGOING, null),
            OnPopularQuery = OnPopularQuery(ORDER_BY_POPULAR, null, null),
            OnCompletedQuery = OnCompletedQuery(ORDER_BY_POPULAR, STATUS_BY_FINAL, null,)
        )
    )

    private var newPagingSource: PagingSource<*, *>? = null


    fun setQueriesOnGoing(genre: String?) {
        _queries.tryEmit(
            _queries.value.copy(
                OnGoingQuery = _queries.value.OnGoingQuery.copy(
                    genre = genre
                )
            )
        )
    }

    fun setQueriesOnPopular(genre: String?) {
        _queries.tryEmit(
            _queries.value.copy(
                OnPopularQuery = _queries.value.OnPopularQuery.copy(
                    genre = genre,
                )
            )
        )
    }

    fun setQueriesOnCompleted( genre: String?) {
        _queries.tryEmit(
            _queries.value.copy(
                OnCompletedQuery = _queries.value.OnCompletedQuery.copy(
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


    private fun newPagerOnGoing(queries: PagerQuery): Pager<Int, Manga> {
        return Pager(PagingConfig(Constants.MORE_PAGE_SIZE, enablePlaceholders = false)) {
            getMorePage.invoke(
                order = queries.OnGoingQuery.order,
                status = queries.OnGoingQuery.status,
                genre =  queries.OnGoingQuery.genre
            ).also { newPagingSource = it }
        }
    }

    private fun newPagerOnPopular(queries: PagerQuery): Pager<Int, Manga> {
        return Pager(PagingConfig(Constants.MORE_PAGE_SIZE, enablePlaceholders = false)) {
            getMorePage.invoke(
                order = queries.OnPopularQuery.order,
                status = queries.OnPopularQuery.status,
                genre =  queries.OnPopularQuery.genre
            ).also { newPagingSource = it }
        }
    }

    private fun newPagerOnCompleted(queries: PagerQuery): Pager<Int, Manga> {
        return Pager(PagingConfig(Constants.MORE_PAGE_SIZE, enablePlaceholders = false)) {
            getMorePage.invoke(
                order = queries.OnCompletedQuery.order,
                status = queries.OnCompletedQuery.status,
                genre =  queries.OnCompletedQuery.genre
            ).also { newPagingSource = it }
        }
    }

}