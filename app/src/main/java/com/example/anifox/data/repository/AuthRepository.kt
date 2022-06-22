package com.example.anifox.data.repository

import com.example.anifox.core.error.ResponseError
import com.example.anifox.core.safeCall
import com.example.anifox.core.wrapper.Resource
import com.example.anifox.data.remote.api.UserService
import com.example.anifox.data.remote.api.retrofit.AuthDataSource
import com.example.anifox.data.remote.models.response.AuthResponse
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val userService: UserService
): AuthDataSource {

    override suspend fun getTokens(authCode: String): Resource<AuthResponse> {
        return when (val response = safeCall { userService.getTokens(authCode = authCode)}) {
            is AuthResponse -> {
                Timber.d(response.toString())

                Resource.Success(data =  response)

            }
            else -> {
                Resource.Error(message = ResponseError.ClientErrorException.message.toString())
            }
        }
    }

    override suspend fun updateTokens(refreshToken: String): Resource<AuthResponse> {
        return when(val response = safeCall { userService.updateTokens(refreshToken = refreshToken) }) {
            is AuthResponse -> {
                Resource.Success( data = response)
            }
            else -> {
                Resource.Error(message = ResponseError.ClientErrorException.message.toString())
            }
        }
    }

}