package com.example.anifox.presentation.morePage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.anifox.domain.model.manga.Manga
import com.example.anifox.domain.useCase.morePage.MangaGenresUseCase
import com.example.anifox.domain.useCase.morePage.MorePageUseCase
import com.example.anifox.presentation.morePage.state.MorePagerState
import com.example.anifox.presentation.morePage.state.genres.OnGenres
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
    private val getMangaGenres: MangaGenresUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MorePagerState(
        onGoingQuery = OnGoingQuery(ORDER_BY_POPULAR, STATUS_BY_ONGOING, null),
        onPopularQuery = OnPopularQuery(ORDER_BY_POPULAR, null, null),
        onCompletedQuery = OnCompletedQuery(ORDER_BY_POPULAR, STATUS_BY_FINAL, null),
        onGenres = OnGenres()
    ))
    val state = _state.asStateFlow()

    private var newPagingSource: PagingSource<*, *>? = null

    fun getGenres(){
        getMangaGenres.invoke().onEach { value ->
            _state.tryEmit(
                _state.value.copy(
                    onGenres = _state.value.onGenres.copy(
                        isLoading = false,
                        data = value.data
                    )
                )
            )
        }.launchIn(viewModelScope)
    }


    fun setQueriesOnGoing(genre: String?) {
        _state.tryEmit(
            _state.value.copy(
                onGoingQuery = _state.value.onGoingQuery.copy(
                    genre = genre
                )
            )
        )
    }

    fun setQueriesOnPopular(genre: String?) {
        _state.tryEmit(
            _state.value.copy(
                onPopularQuery = _state.value.onPopularQuery.copy(
                    genre = genre,
                )
            )
        )
    }

    fun setQueriesOnCompleted( genre: String?) {
        _state.tryEmit(
            _state.value.copy(
                onCompletedQuery = _state.value.onCompletedQuery.copy(
                    genre = genre
                )
            )
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getMangasOnGoingPaging(): StateFlow<PagingData<Manga>> {
        return _state
            .map(::newPagerOnGoing)
            .flatMapLatest { pager -> pager.flow }
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getMangasOnPopularPaging(): StateFlow<PagingData<Manga>> {
        return _state
            .map(::newPagerOnPopular)
            .flatMapLatest { pager -> pager.flow }
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getMangasOnCompletedPaging(): StateFlow<PagingData<Manga>> {
        return  _state
            .map(::newPagerOnCompleted)
            .flatMapLatest { pager -> pager.flow }
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
    }


    private fun newPagerOnGoing(queries: MorePagerState): Pager<Int, Manga> {
        return Pager(PagingConfig(Constants.MORE_PAGE_SIZE, enablePlaceholders = false)) {
            getMorePage.invoke(
                order = queries.onGoingQuery.order,
                status = queries.onGoingQuery.status,
                genre =  queries.onGoingQuery.genre,
                token = null
            ).also { newPagingSource = it }
        }
    }

    private fun newPagerOnPopular(queries: MorePagerState): Pager<Int, Manga> {
        return Pager(PagingConfig(Constants.MORE_PAGE_SIZE, enablePlaceholders = false)) {
            getMorePage.invoke(
                order = queries.onPopularQuery.order,
                status = queries.onPopularQuery.status,
                genre =  queries.onPopularQuery.genre,
                token = null
            ).also { newPagingSource = it }
        }
    }

    private fun newPagerOnCompleted(queries: MorePagerState): Pager<Int, Manga> {
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