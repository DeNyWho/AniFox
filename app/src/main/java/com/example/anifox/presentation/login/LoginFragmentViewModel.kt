package com.example.anifox.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anifox.domain.model.auth.AuthState
import com.example.anifox.domain.useCase.login.GetTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginFragmentViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
): ViewModel() {
    private val _tokenState : MutableSharedFlow<AuthState> = MutableSharedFlow()
    val tokenState = _tokenState.asSharedFlow()


    suspend fun getTokens(authCode: String){
        getTokenUseCase.invoke(authCode).onEach { value ->
            _tokenState.tryEmit(value)
        }.launchIn(viewModelScope)
    }


}