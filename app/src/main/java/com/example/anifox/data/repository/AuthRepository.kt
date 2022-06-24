package com.example.anifox.data.repository

//@Singleton
//class AuthRepository @Inject constructor(
//    private val userApi: UserApi
//): AuthDataSource {
//
//    override suspend fun getTokens(authCode: String): Resource<AuthResponse> {
//        return when (val response = safeCall { userApi.getTokens(authCode = authCode)}) {
//            is AuthResponse -> {
//                Timber.d(response.toString())
//
//                Resource.Success(data =  response)
//
//            }
//            else -> {
//                Resource.Error(message = ResponseError.ClientErrorException.toString())
//            }
//        }
//    }
//
//    override suspend fun updateTokens(refreshToken: String): Resource<AuthResponse> {
//        return when(val response = safeCall { userApi.updateTokens(refreshToken = refreshToken) }) {
//            is AuthResponse -> {
//                Resource.Success( data = response)
//            }
//            else -> {
//                Resource.Error(message = ResponseError.ClientErrorException.toString())
//            }
//        }
//    }
//
//}