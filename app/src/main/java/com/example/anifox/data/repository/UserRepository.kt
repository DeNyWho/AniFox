package com.example.anifox.data.repository

import com.example.anifox.data.remote.api.UserApi
import com.example.anifox.domain.model.responses.UserResponse
import com.example.anifox.domain.model.user.UserSignIn
import com.example.anifox.domain.model.user.UserSignUp
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userApi: UserApi
) {

    suspend fun signIn(user: UserSignIn): Response<UserResponse> {
        return userApi.signIn(user)
    }

    suspend fun signUp(user: UserSignUp): Response<UserResponse> {
        return userApi.signUp(user)
    }

}