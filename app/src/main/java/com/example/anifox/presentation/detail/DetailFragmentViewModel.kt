package com.example.anifox.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anifox.domain.useCase.dataStore.GetTokenUseCase
import com.example.anifox.domain.useCase.detail.AddFavouriteUseCase
import com.example.anifox.domain.useCase.detail.GetDetailsUseCase
import com.example.anifox.domain.useCase.detail.GetLinkedUseCase
import com.example.anifox.domain.useCase.detail.GetSimilarUseCase
import com.example.anifox.presentation.detail.state.DetailState
import com.example.anifox.presentation.detail.state.detail.ContentDetailsState
import com.example.anifox.presentation.detail.state.favourite.DetailFavouriteState
import com.example.anifox.presentation.detail.state.linked.DetailLinkedState
import com.example.anifox.presentation.detail.state.similar.DetailSimilarState
import com.example.anifox.presentation.detail.state.token.DetailTokenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailFragmentViewModel @Inject constructor(
    private val getDetails: GetDetailsUseCase,
    private val addFavourite: AddFavouriteUseCase,
    private val getTokenUseCase: GetTokenUseCase,
    private val getSimilar: GetSimilarUseCase,
    private val getLinked: GetLinkedUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(
        DetailState(
            contentDetailsState = ContentDetailsState(isLoading = true, data = null),
            detailFavouriteState = DetailFavouriteState(isLoading = true, data = null),
            detailTokenState = DetailTokenState(token = null, isLoading = true),
            detailLinkedState = DetailLinkedState(data = null, isLoading = true),
            detailSimilarState = DetailSimilarState(data = null, isLoading = true)
        )
    )
    val state = _state.asStateFlow()

    private val _queries = MutableStateFlow(0)

    fun setQueries(malId: Int) {
        _queries.tryEmit(malId)
    }

    fun addFavourite(id: Int, status: String, token: String){
        addFavourite.invoke(id = id, status = status, token = token).onEach { value ->
            _state.tryEmit(
                _state.value.copy(
                    detailFavouriteState = _state.value.detailFavouriteState.copy(
                        isLoading = false,
                        data = value.data
                    )
                )
            )
        } .launchIn(viewModelScope)
    }

    fun getDetails(){
        getDetails.invoke(_queries.value).onEach { value ->
            _state.tryEmit(
                _state.value.copy(
                    contentDetailsState = _state.value.contentDetailsState.copy(
                        isLoading = false,
                        data = value.data
                    )
                )
            )
        } .launchIn(viewModelScope)
    }

    fun getSimilar(){
        getSimilar.invoke(_queries.value).onEach { value ->
            _state.tryEmit(
                _state.value.copy(
                    detailSimilarState = _state.value.detailSimilarState.copy(
                        isLoading = false,
                        data = value.data
                    )
                )
            )
        } .launchIn(viewModelScope)
    }

    fun getLinked(){
        getLinked.invoke(_queries.value).onEach { value ->
            _state.tryEmit(
                _state.value.copy(
                    detailLinkedState = _state.value.detailLinkedState.copy(
                        isLoading = false,
                        data = value.data
                    )
                )
            )
        } .launchIn(viewModelScope)
    }

    fun getTokenFromPreferences(){
        getTokenUseCase.invoke().onEach { value ->
            _state.tryEmit(
                _state.value.copy(
                    detailTokenState = _state.value.detailTokenState.copy(
                        token = value?.token,
                        isLoading = value?.isLoading == true
                    )
                )
            )
        }.launchIn(viewModelScope)
    }

}