package com.example.anifox.data.repository

import com.example.anifox.data.remote.api.AuthApi
import com.example.anifox.domain.model.responses.BasicBooleanResponse
import com.example.anifox.domain.model.responses.BasicResponse
import com.example.anifox.domain.model.responses.UserResponse
import com.example.anifox.domain.model.user.UserSignIn
import com.example.anifox.domain.model.user.UserSignUp
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authApi: AuthApi
) {

    suspend fun signIn(user: UserSignIn): Response<UserResponse> {
        return authApi.signIn(user)
    }

    suspend fun signUp(user: UserSignUp): Response<UserResponse> {
        return authApi.signUp(user)
    }

    suspend fun sendRecoverInstructions(email: String): Response<BasicResponse> {
        return authApi.sendRecoverInstructions(email)
    }

    suspend fun confirmationPassword(email: String): Response<UserResponse> {
        return authApi.confirmationPassword(email)
    }

    suspend fun authorizedFindByUsername(name: String): Response<BasicBooleanResponse> {
        return authApi.authorizedFindUserByUserName(name)
    }



}