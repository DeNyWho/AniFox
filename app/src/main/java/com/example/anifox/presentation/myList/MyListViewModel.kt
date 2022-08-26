package com.example.anifox.presentation.myList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.anifox.domain.model.manga.Manga
import com.example.anifox.domain.useCase.dataStore.GetTokenUseCase
import com.example.anifox.domain.useCase.myList.ListUseCase
import com.example.anifox.presentation.myList.state.*
import com.example.anifox.presentation.splash.state.TokenState
import com.example.anifox.util.Constants
import com.example.anifox.util.Constants.STATUS_LIST_All
import com.example.anifox.util.Constants.STATUS_LIST_Completed
import com.example.anifox.util.Constants.STATUS_LIST_HoldOn
import com.example.anifox.util.Constants.STATUS_LIST_Watching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MyListViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
    private val getListUseCase: ListUseCase
): ViewModel(){

    private var _state = MutableStateFlow(MyListState(
        tokenState = TokenState(null),
    ))
    val state = _state.asStateFlow()

    private val _queries = MutableStateFlow(
        PagerQueryList(
            listCompletedState = ListCompletedState(null, STATUS_LIST_Completed, null, null),
            listWatchingState = ListWatchingState(null, STATUS_LIST_Watching, null, null),
            listOnHoldState = ListOnHoldState(null, STATUS_LIST_HoldOn, null, null),
            listAllState = ListAllState(null, STATUS_LIST_All, null, null),
        )
    )

    private var newPagingSource: PagingSource<*, *>? = null


    fun getTokenFromPreferences(){
        getTokenUseCase.invoke().onEach { value ->
            _state.tryEmit(
                _state.value.copy(
                    tokenState = _state.value.tokenState.copy(
                        token = value?.token,
                        isLoading = value?.isLoading == true
                    )
                )
            )
            _queries.tryEmit(
                _queries.value.copy(
                    listWatchingState = _queries.value.listWatchingState.copy(
                        token = value?.token
                    ),
                    listCompletedState = _queries.value.listCompletedState.copy(
                        token = value?.token
                    ),
                    listOnHoldState = _queries.value.listOnHoldState.copy(
                        token = value?.token
                    ),
                    listAllState = _queries.value.listAllState.copy(
                        token = value?.token
                    ),
                )
            )
        }.launchIn(viewModelScope)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getListOnAllPaging(): StateFlow<PagingData<Manga>> {
        return  _queries
            .map(::newPagerListAll)
            .flatMapLatest { pager -> pager.flow }
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
    }


    private fun newPagerListAll(queries: PagerQueryList): Pager<Int, Manga> {
        return Pager(PagingConfig(Constants.MORE_PAGE_SIZE, enablePlaceholders = false)) {
            getListUseCase.invoke(
                order = null,
                status = queries.listAllState.status,
                genre =  null,
                token = queries.listAllState.token
            ).also { newPagingSource = it }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getListOnWatchingPaging(): StateFlow<PagingData<Manga>> {
        return  _queries
            .map(::newPagerListWatching)
            .flatMapLatest { pager -> pager.flow }
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
    }


    private fun newPagerListWatching(queries: PagerQueryList): Pager<Int, Manga> {
        return Pager(PagingConfig(Constants.MORE_PAGE_SIZE, enablePlaceholders = false)) {
            getListUseCase.invoke(
                order = null,
                status = queries.listWatchingState.status,
                genre =  null,
                token = queries.listWatchingState.token
            ).also { newPagingSource = it }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getListOnCompletedPaging(): StateFlow<PagingData<Manga>> {
        return  _queries
            .map(::newPagerListCompleted)
            .flatMapLatest { pager -> pager.flow }
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
    }


    private fun newPagerListCompleted(queries: PagerQueryList): Pager<Int, Manga> {
        return Pager(PagingConfig(Constants.MORE_PAGE_SIZE, enablePlaceholders = false)) {
            getListUseCase.invoke(
                order = null,
                status = queries.listCompletedState.status,
                genre =  null,
                token = queries.listCompletedState.token
            ).also { newPagingSource = it }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getListOnHoldPaging(): StateFlow<PagingData<Manga>> {
        return  _queries
            .map(::newPagerListOnHold)
            .flatMapLatest { pager -> pager.flow }
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
    }


    private fun newPagerListOnHold(queries: PagerQueryList): Pager<Int, Manga> {
        return Pager(PagingConfig(Constants.MORE_PAGE_SIZE, enablePlaceholders = false)) {
            getListUseCase.invoke(
                order = null,
                status = queries.listOnHoldState.status,
                genre =  null,
                token = queries.listOnHoldState.token
            ).also { newPagingSource = it }
        }
    }

}