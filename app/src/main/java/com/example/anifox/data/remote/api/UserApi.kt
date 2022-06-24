package com.example.anifox.data.remote.api

import com.example.anifox.BuildConfig
import com.example.anifox.data.remote.models.response.AuthResponse
import com.example.anifox.util.Constants
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserApi {

    @FormUrlEncoded
    @POST("oauth/token")
    suspend fun getTokens(
        @Field("grant_type") grantType: String = BuildConfig.AUTH_CODE,
        @Field("client_id") clientId: String = BuildConfig.CLIENT_ID,
        @Field("client_secret") clientSecret: String = BuildConfig.CLIENT_SECRET,
        @Field("code") authCode: String,
        @Field("redirect_uri") redirectUri: String = Constants.REDIRECT_URI
    ): Response<AuthResponse>

    @FormUrlEncoded
    @POST("oauth/token")
    suspend fun updateTokens(
        @Field("grant_type") grantType: String = "refresh_token",
        @Field("client_id") clientId: String = BuildConfig.CLIENT_ID,
        @Field("client_secret") clientSecret: String = BuildConfig.CLIENT_SECRET,
        @Field("refresh_token") refreshToken: String
    ): Response<AuthResponse>
}