package com.example.anifox.presentation.signUp.state

import com.example.anifox.domain.model.user.User

data class UserSignUpState(
    val data: List<User>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)