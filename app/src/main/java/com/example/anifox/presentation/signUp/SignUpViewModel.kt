package com.example.anifox.presentation.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anifox.domain.useCase.login.SignUpUseCase
import com.example.anifox.presentation.signUp.state.UserSignUpState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUp: SignUpUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UserSignUpState(isLoading = true, data = null))
    val state = _state.asStateFlow()

    fun signUp(username: String, password: String, email: String) {
        signUp.invoke(username = username, password = password, email = email).onEach { value ->
            _state.tryEmit(
                _state.value.copy(
                    isLoading = false,
                    data = value.data
                )
            )
        }.launchIn(viewModelScope)
    }
}