package com.example.anifox.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anifox.R
import com.example.anifox.domain.useCase.search.RandomSearchUseCase
import com.example.anifox.domain.useCase.search.SearchUseCase
import com.example.anifox.presentation.search.state.SearchState
import com.example.anifox.presentation.search.state.random.RandomSearchState
import com.example.anifox.presentation.search.state.search.SearcherState
import com.example.anifox.util.Constants
import com.example.anifox.util.StringResourcesProvider
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
) : ViewModel() {
    private val _state = MutableStateFlow(
        SearchState(
            randomState = RandomSearchState(isLoading = true, data = null),
            search = SearcherState(isLoading = true, data = null)
        )
    )
    val state = _state.asStateFlow()

    private val _queries = MutableStateFlow("")

    fun setQueries(query: String) {
        _queries.tryEmit(query)
    }

    fun getSearch(){
        getSearch.invoke(_queries.value).onEach { value ->
            println("VALUE = ${value.data}")
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