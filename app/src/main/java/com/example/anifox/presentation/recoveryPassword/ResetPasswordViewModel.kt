package com.example.anifox.presentation.recoveryPassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anifox.domain.useCase.recoveryPassword.ConfirmationPasswordUseCase
import com.example.anifox.domain.useCase.recoveryPassword.SendRecoverInstructionsUseCase
import com.example.anifox.presentation.recoveryPassword.state.PasswordConfirmationState
import com.example.anifox.presentation.recoveryPassword.state.RecoveryPasswordState
import com.example.anifox.presentation.recoveryPassword.state.SendInstructionsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val sendRecoverInstructions: SendRecoverInstructionsUseCase,
    private val ConfirmationPasswordUseCase: ConfirmationPasswordUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(RecoveryPasswordState(
        sendInstructionsState = SendInstructionsState(message = ""),
        passwordConfirmationState = PasswordConfirmationState()
    ))
    val state = _state.asStateFlow()

    fun confirmationPassword(email: String){
        ConfirmationPasswordUseCase.invoke(email = email).onEach { value ->
            _state.tryEmit(
                _state.value.copy(
                    passwordConfirmationState = _state.value.passwordConfirmationState.copy(
                        data = value.data,
                        isLoading = value.isLoading,
                        error = value.error
                    )
                )
            )
        }.launchIn(viewModelScope)
    }

    fun sendInstructions(email: String) {
        sendRecoverInstructions.invoke(email = email).onEach { value ->
            _state.tryEmit(
                _state.value.copy(
                    sendInstructionsState = _state.value.sendInstructionsState.copy(
                        message = value.message
                    )
                )
            )
        }.launchIn(viewModelScope)
    }



}