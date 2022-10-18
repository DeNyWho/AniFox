package com.example.anifox.presentation.rating

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.anifox.domain.model.manga.Manga
import com.example.anifox.domain.useCase.morePage.MorePageUseCase
import com.example.anifox.presentation.rating.state.RatingQueryState
import com.example.anifox.presentation.rating.state.onCompleted.OnCompletedRating
import com.example.anifox.presentation.rating.state.onGoing.OnGoingRating
import com.example.anifox.presentation.rating.state.onPopular.OnPopularRating
import com.example.anifox.presentation.rating.state.onViews.OnViewsRating
import com.example.anifox.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class RatingViewModel @Inject constructor(
    private val getMorePage: MorePageUseCase,
) : ViewModel() {

    private val _queries = MutableStateFlow(
        RatingQueryState(
            onGoingQuery = OnGoingRating(Constants.ORDER_BY_POPULAR, Constants.STATUS_BY_ONGOING, null),
            onPopularQuery = OnPopularRating(Constants.ORDER_BY_POPULAR, null, null),
            onCompletedQuery = OnCompletedRating(Constants.ORDER_BY_POPULAR, Constants.STATUS_BY_FINAL, null),
            onViewsQuery = OnViewsRating(Constants.ORDER_BY_VIEWS, null, null)
        )
    )

    private var newPagingSource: PagingSource<*, *>? = null

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

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getMangasOnViewsPaging(): StateFlow<PagingData<Manga>> {
        return  _queries
            .map(::newPagerOnViews)
            .flatMapLatest { pager -> pager.flow }
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
    }


    private fun newPagerOnGoing(queries: RatingQueryState): Pager<Int, Manga> {
        return Pager(PagingConfig(Constants.MORE_PAGE_SIZE, enablePlaceholders = false)) {
            getMorePage.invoke(
                order = queries.onGoingQuery.order,
                status = queries.onGoingQuery.status,
                genre =  queries.onGoingQuery.genre,
                token = null
            ).also { newPagingSource = it }
        }
    }

    private fun newPagerOnPopular(queries: RatingQueryState): Pager<Int, Manga> {
        return Pager(PagingConfig(Constants.MORE_PAGE_SIZE, enablePlaceholders = false)) {
            getMorePage.invoke(
                order = queries.onPopularQuery.order,
                status = queries.onPopularQuery.status,
                genre =  queries.onPopularQuery.genre,
                token = null
            ).also { newPagingSource = it }
        }
    }

    private fun newPagerOnCompleted(queries: RatingQueryState): Pager<Int, Manga> {
        return Pager(PagingConfig(Constants.MORE_PAGE_SIZE, enablePlaceholders = false)) {
            getMorePage.invoke(
                order = queries.onCompletedQuery.order,
                status = queries.onCompletedQuery.status,
                genre =  queries.onCompletedQuery.genre,
                token = null
            ).also { newPagingSource = it }
        }
    }

    private fun newPagerOnViews(queries: RatingQueryState): Pager<Int, Manga> {
        return Pager(PagingConfig(Constants.MORE_PAGE_SIZE, enablePlaceholders = false)) {
            getMorePage.invoke(
                order = queries.onViewsQuery.order,
                status = queries.onViewsQuery.status,
                genre =  queries.onViewsQuery.genre,
                token = null
            ).also { newPagingSource = it }
        }
    }

}