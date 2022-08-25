package com.example.anifox.domain.useCase.dataStore

import com.example.anifox.data.repository.DataStoreRepository
import com.example.anifox.presentation.splash.state.TokenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(
    private val dataStoreOperationsImpl: DataStoreRepository
) {
    operator fun invoke(): Flow<TokenState?> {
        return flow {
            emit(TokenState(isLoading = true))

            emit(TokenState(isLoading = false, token = dataStoreOperationsImpl.getJwtToken()))
        }.flowOn(Dispatchers.IO)
    }
}
