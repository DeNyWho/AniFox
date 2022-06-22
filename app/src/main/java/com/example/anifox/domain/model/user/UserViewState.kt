package com.example.anifox.domain.model.user

import javax.annotation.concurrent.Immutable

@Immutable
data class UserViewState(
    val authStatus: UserAuthState = UserAuthState.LOGGED_OUT,
    val user: User? = null
){
    companion object {
        val empty = UserViewState
    }
}