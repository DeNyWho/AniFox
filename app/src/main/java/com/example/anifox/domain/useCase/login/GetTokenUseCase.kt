package com.example.anifox.domain.useCase.login

//class GetTokenUseCase @Inject constructor(
//    private val authRepository: AuthRepository,
//    private val dataStoreOperationsImpl: DataStoreOperationsImpl
//) {
//    suspend operator fun invoke(authCode: String): Flow<AuthState> {
//        return flow {
//            emit(AuthState(isLoading = true))
//
//            val state = when (val res = authRepository.getTokens(authCode = authCode)) {
//                is Resource.Success -> {
//                    if (res.data != null) {
//                        dataStoreOperationsImpl.saveToken(res.data.accessToken)
//                        dataStoreOperationsImpl.saveRefreshToken(res.data.refreshToken)
//                        Timber.d(res.message)
//                        AuthState(
//                            token = res.data.accessToken,
//                            refreshToken = res.data.refreshToken,
//                            isLoading = false
//                        )
//                    } else {
//                        AuthState(error = res.message.toString(), isLoading = false)
//                    }
//                }
//                is Resource.Error -> {
//                    Timber.d(res.message)
//                    AuthState(error = res.message.toString(), isLoading = false)
//                }
//                is Resource.Loading -> AuthState(isLoading = true)
//            }
//
//            emit(state)
//        }.flowOn(Dispatchers.IO)
//    }
//}