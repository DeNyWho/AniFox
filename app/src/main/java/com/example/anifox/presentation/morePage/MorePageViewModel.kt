package com.example.anifox.presentation.morePage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.anifox.domain.model.anime.Anime
import com.example.anifox.domain.model.common.PagerQuery
import com.example.anifox.domain.useCase.morePage.MorePageUseCase
import com.example.anifox.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MorePageViewModel @Inject constructor(
    private val getMorePage: MorePageUseCase,
) : ViewModel() {

    private val _queries = MutableStateFlow(PagerQuery(null, null))

    private var newPagingSource: PagingSource<*, *>? = null

    fun setQueries(order: String?, status: String?) {
        _queries.tryEmit(PagerQuery(order, status))
    }

    val animes: StateFlow<PagingData<Anime>> = _queries
        .map(::newPager)
        .flatMapLatest { pager -> pager.flow }
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    private fun newPager(queries: PagerQuery): Pager<Int, Anime> {
        return Pager(PagingConfig(Constants.MORE_PAGE_SIZE, enablePlaceholders = false)) {
            Timber.d("newPager: order = ${queries.order}, status = ${queries.status}")
            getMorePage.invoke(queries.order, queries.status).also { newPagingSource = it }
        }
    }

}