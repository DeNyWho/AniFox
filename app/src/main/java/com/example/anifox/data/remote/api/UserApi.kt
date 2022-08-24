package com.example.anifox.data.remote.api

import com.example.anifox.domain.model.responses.UserResponse
import com.example.anifox.domain.model.user.UserSignIn
import com.example.anifox.domain.model.user.UserSignUp
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("auth/signup")
    suspend fun signUp(
        @Body user: UserSignUp
    ): Response<UserResponse>

    @POST("auth/signin")
    suspend fun signIn(
        @Body user: UserSignIn
    ): Response<UserResponse>
}