package com.example.anifox.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anifox.R
import com.example.anifox.domain.useCase.search.OnFinalPopularSearchUseCase
import com.example.anifox.domain.useCase.search.OnGoingPopularSearchUseCase
import com.example.anifox.domain.useCase.search.RandomSearchUseCase
import com.example.anifox.domain.useCase.search.SearchUseCase
import com.example.anifox.presentation.search.state.SearchState
import com.example.anifox.presentation.search.state.random.RandomSearchState
import com.example.anifox.presentation.search.state.search.SearcherState
import com.example.anifox.presentation.search.state.status.OnFinalSearchState
import com.example.anifox.presentation.search.state.status.OnGoingSearchState
import com.example.anifox.util.Constants
import com.example.anifox.util.Constants.REVIEW_LIMIT
import com.example.anifox.util.Constants.SORT_BY_RATE
import com.example.anifox.util.Constants.STATUS_BY_FINAL
import com.example.anifox.util.Constants.STATUS_BY_ONGOING
import com.example.anifox.util.resources.StringResourcesProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val stringResourcesProvider: StringResourcesProvider,
    private val getSearch: SearchUseCase,
    private val getRandom: RandomSearchUseCase,
    private val getOnGoing: OnGoingPopularSearchUseCase,
    private val getOnFinal: OnFinalPopularSearchUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(
        SearchState(
            randomState = RandomSearchState(isLoading = true, data = null),
            search = SearcherState(isLoading = true, data = null),
            onFinal = OnFinalSearchState(isLoading = true, data = null),
            onGoing = OnGoingSearchState(isLoading = true, data = null),
        )
    )
    val state = _state.asStateFlow()

    private val _queries = MutableStateFlow("")

    fun setQueries(query: String) {
        _queries.tryEmit(query)
    }

    fun clearSearch(){
        _state.tryEmit(
            _state.value.copy(
                search = _state.value.search.copy(
                    isLoading = true,
                    data = null,

                )
            )
        )
    }

    fun clearData(){
        _state.tryEmit(
            _state.value.copy(
                onGoing = _state.value.onGoing.copy(
                    isLoading = true,
                    data = null
                )
            )
        )
        _state.tryEmit(
            _state.value.copy(
                onFinal = _state.value.onFinal.copy(
                    isLoading = true,
                    data = null
                )
            )
        )
    }

    fun getPopularOnGoing(){
        getOnGoing.invoke(genre = null, order = SORT_BY_RATE, status = STATUS_BY_ONGOING, countCard = REVIEW_LIMIT).onEach { value ->
            _state.tryEmit(
                _state.value.copy(
                    onGoing = _state.value.onGoing.copy(
                        isLoading = false,
                        data = value.data
                    )
                )
            )
        } .launchIn(viewModelScope)
    }

    fun getPopularOnFinal(){
        getOnFinal.invoke(genre = null, order = SORT_BY_RATE, status = STATUS_BY_FINAL, countCard = REVIEW_LIMIT).onEach { value ->
            _state.tryEmit(
                _state.value.copy(
                    onFinal = _state.value.onFinal.copy(
                        isLoading = false,
                        data = value.data
                    )
                )
            )
        } .launchIn(viewModelScope)
    }

    fun getSearch(){
        getSearch.invoke(_queries.value).onEach { value ->
            _state.tryEmit(
                _state.value.copy(
                    search = _state.value.search.copy(
                        isLoading = false,
                        data = value.data
                    )
                )
            )
        } .launchIn(viewModelScope)
    }

    fun getRandom(){
        getRandom.invoke(genre = stringResourcesProvider.getString(R.string.Genre_Random), order = null, status = null, countCard = Constants.REVIEW_LIMIT_RANDOMIZE).onEach { value ->
            _state.tryEmit(
                _state.value.copy(
                    randomState = _state.value.randomState.copy(
                        isLoading = false,
                        data = value.data
                    )
                )
            )
        }.launchIn(viewModelScope)
    }


}