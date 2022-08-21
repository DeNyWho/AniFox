package com.example.anifox.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anifox.domain.useCase.detail.GetDetailsUseCase
import com.example.anifox.presentation.detail.state.ContentDetailsState
import com.example.anifox.presentation.detail.state.DetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailFragmentViewModel @Inject constructor(
    private val getDetails: GetDetailsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(
        DetailState(
            contentDetailsState = ContentDetailsState(isLoading = true, data = null)
        )
    )
    val state = _state.asStateFlow()

    private val _queries = MutableStateFlow(0)

    fun setQueries(malId: Int) {
        _queries.tryEmit(malId)
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

}