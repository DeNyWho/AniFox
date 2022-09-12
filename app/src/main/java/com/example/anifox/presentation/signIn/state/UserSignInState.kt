package com.example.anifox.presentation.signIn.state

import com.example.anifox.domain.model.user.User

data class UserSignInState(
    val data: List<User>? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val message: String? = null,
)