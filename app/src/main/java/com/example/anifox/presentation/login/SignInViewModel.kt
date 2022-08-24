package com.example.anifox.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anifox.domain.useCase.login.SignInUseCase
import com.example.anifox.presentation.login.state.UserSignInState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signIn: SignInUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UserSignInState(isLoading = true, data = null))
    val state = _state.asStateFlow()

    fun signIn(username: String, password: String) {
        signIn.invoke(username = username, password = password).onEach { value ->
            _state.tryEmit(
                _state.value.copy(
                    isLoading = false,
                    data = value.data
                )
            )
        }.launchIn(viewModelScope)
    }
}