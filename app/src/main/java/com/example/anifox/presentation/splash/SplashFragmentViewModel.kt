package com.example.anifox.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anifox.domain.useCase.splash.ReadOnBoardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SplashFragmentViewModel @Inject constructor(
    private val readOnBoardingUseCase: ReadOnBoardingUseCase
) : ViewModel() {
    private val _onBoardingCompleted = MutableStateFlow(false)

    init {
        readOnBoardingUseCase().onEach { value ->
            _onBoardingCompleted.tryEmit(value)
        }.launchIn(viewModelScope)
    }
}