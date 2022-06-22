package com.example.anifox.domain.model.user

enum class UserAuthState {
    LOGGED_IN,
    LOGGED_LOADING,
    LOGGED_ERROR,
    LOGGED_OUT;

    val isAuthorized : Boolean
        get() = this == LOGGED_IN
}