package com.example.anifox.domain.useCase.splash

import com.example.anifox.data.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadOnBoardingUseCase @Inject constructor (
    private val dataStoreRepository: DataStoreRepository
) {
    operator fun invoke(): Flow<Boolean> {
        return dataStoreRepository.readOnBoardingState()
    }
}