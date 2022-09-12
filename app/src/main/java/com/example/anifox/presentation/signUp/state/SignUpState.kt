package com.example.anifox.presentation.signUp.state

import com.example.anifox.presentation.signUp.state.exist.NameExistsState
import com.example.anifox.presentation.signUp.state.signUp.UserSignUpState

data class SignUpState(
    val userSignUpState: UserSignUpState,
    val nameExistsState: NameExistsState
)