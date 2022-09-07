package com.example.anifox.presentation.recoveryPassword.state

import com.example.anifox.domain.model.user.User

data class PasswordConfirmationState(
    val data: List<User>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)