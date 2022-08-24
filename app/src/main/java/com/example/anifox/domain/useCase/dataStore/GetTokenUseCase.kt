package com.example.anifox.domain.useCase.dataStore

import com.example.anifox.data.repository.DataStoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(
    private val dataStoreOperationsImpl: DataStoreRepository
) {
    operator fun invoke(): Flow<String?> {
        return flow {
            emit(dataStoreOperationsImpl.getJwtToken())
        }.flowOn(Dispatchers.IO)
    }
}
