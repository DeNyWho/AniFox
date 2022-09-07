package com.example.anifox.presentation.recoveryPassword.state

data class RecoveryPasswordState(
    val sendInstructionsState: SendInstructionsState,
    val passwordConfirmationState: PasswordConfirmationState
)