package com.example.anifox.presentation.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anifox.domain.useCase.dataStore.GetTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase
): ViewModel(){
    private var _token: MutableLiveData<String> = MutableLiveData()
    val token: LiveData<String>
        get() = _token

    fun getTokenFromPreferences(){
        println("ВАТ")
        getTokenUseCase.invoke().onEach {
            _token.value = it
        }.launchIn(viewModelScope)
    }
}

//@HiltViewModel
//class SplashFragmentViewModel @Inject constructor(
//    private val readOnBoardingUseCase: ReadOnBoardingUseCase
//) : ViewModel() {
//    private val _onBoardingCompleted = MutableStateFlow(false)
//
//    init {
//        readOnBoardingUseCase().onEach { value ->
//            _onBoardingCompleted.tryEmit(value)
//        }.launchIn(viewModelScope)
//    }
//}