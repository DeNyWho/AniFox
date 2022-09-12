package com.example.anifox.data.remote.api

import com.example.anifox.domain.model.responses.BasicBooleanResponse
import com.example.anifox.domain.model.responses.BasicResponse
import com.example.anifox.domain.model.responses.UserResponse
import com.example.anifox.domain.model.user.UserSignIn
import com.example.anifox.domain.model.user.UserSignUp
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApi {
    @POST("auth/signup")
    suspend fun signUp(
        @Body user: UserSignUp
    ): Response<UserResponse>

    @POST("auth/signin")
    suspend fun signIn(
        @Body user: UserSignIn
    ): Response<UserResponse>

    @POST("auth/confirmationPassword")
    suspend fun confirmationPassword(
        @Query("email") email: String
    ): Response<UserResponse>

    @POST("auth/sendRecoverInstructions")
    suspend fun sendRecoverInstructions(
        @Query("email") email: String
    ): Response<BasicResponse>

    @POST("auth/findUserByUserName")
    suspend fun authorizedFindUserByUserName(
        @Query("name") name: String
    ): Response<BasicBooleanResponse>

}