package com.example.anifox.domain.useCase.splash

import com.example.anifox.data.repository.DataStoreRepository

class SaveOnBoardingUseCase(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke(completed: Boolean) {
        dataStoreRepository.saveOnBoardingState(completed = completed)
    }
}