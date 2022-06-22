package com.example.anifox.domain.useCase.login

import com.example.anifox.core.wrapper.Resource
import com.example.anifox.data.repository.AuthRepository
import com.example.anifox.domain.model.auth.AuthState
import com.example.anifox.util.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(
    private val repository: AuthRepository,
    private val sessionManager: SessionManager
) {
    suspend operator fun invoke(authCode: String): Flow<AuthState> {
        return flow {
            emit(AuthState(isLoading = true))

            val state = when (val res = repository.getTokens(authCode = authCode)) {
                is Resource.Success -> {
                    if (res.data != null) {
                        sessionManager.saveToken(res.data.accessToken)
                        sessionManager.saveRefreshToken(res.data.refreshToken)
                        Timber.d(res.message)
                        AuthState(
                            token = res.data.accessToken,
                            refreshToken = res.data.refreshToken,
                            isLoading = false
                        )
                    } else {
                        AuthState(error = res.message.toString(), isLoading = false)
                    }
                }
                is Resource.Error -> {
                    Timber.d(res.message)
                    AuthState(error = res.message.toString(), isLoading = false)
                }
                is Resource.Loading -> AuthState(isLoading = true)
            }

            emit(state)
        }.flowOn(Dispatchers.IO)
    }
}