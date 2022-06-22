package com.example.anifox.data.remote.api.retrofit

import com.example.anifox.core.wrapper.Resource
import com.example.anifox.data.remote.models.response.AuthResponse

interface AuthDataSource {

    suspend fun getTokens(authCode: String): Resource<AuthResponse>
    suspend fun updateTokens(refreshToken: String): Resource<AuthResponse>

}