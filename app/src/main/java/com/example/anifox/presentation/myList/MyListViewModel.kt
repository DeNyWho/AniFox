package com.example.anifox.presentation.myList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anifox.domain.useCase.dataStore.GetTokenUseCase
import com.example.anifox.presentation.myList.state.MyListState
import com.example.anifox.presentation.splash.state.TokenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MyListViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase
): ViewModel(){
    private var _state = MutableStateFlow(MyListState(tokenState = TokenState(null)))
    val state = _state.asStateFlow()

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
        }.launchIn(viewModelScope)
    }

}