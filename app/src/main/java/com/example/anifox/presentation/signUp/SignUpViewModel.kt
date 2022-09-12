package com.example.anifox.presentation.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anifox.domain.useCase.login.SignUpFindByNameUseCase
import com.example.anifox.domain.useCase.login.SignUpUseCase
import com.example.anifox.presentation.signUp.state.SignUpState
import com.example.anifox.presentation.signUp.state.exist.NameExistsState
import com.example.anifox.presentation.signUp.state.signUp.UserSignUpState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUp: SignUpUseCase,
    private val SignUpFindByName: SignUpFindByNameUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(
        SignUpState(
            userSignUpState = UserSignUpState(isLoading = true, data = null, message = null),
            nameExistsState = NameExistsState(isLoading = true, data = null, message = null)
    ))
    val state = _state.asStateFlow()

    fun signUp(username: String, password: String, email: String) {
        signUp.invoke(username = username, password = password, email = email).onEach { value ->
            _state.tryEmit(
                _state.value.copy(
                    userSignUpState = _state.value.userSignUpState.copy(
                        isLoading = false,
                        data = value.data,
                        message = value.message
                    )
                )
            )
        }.launchIn(viewModelScope)
    }

    fun checkUserNameIsAvailable(username: String) {
        SignUpFindByName.invoke(name = username).onEach { value ->
            _state.tryEmit(
                _state.value.copy(
                    nameExistsState = _state.value.nameExistsState.copy(
                        isLoading = false,
                        data = value.data,
                        message = value.message
                    )
                )
            )
        }
    }

    fun authNotSuccess() {
        _state.tryEmit(
            _state.value.copy(
                userSignUpState = _state.value.userSignUpState.copy(
                    isLoading = false,
                    data = null,
                    message = null
                )
            )
        )
    }


}

