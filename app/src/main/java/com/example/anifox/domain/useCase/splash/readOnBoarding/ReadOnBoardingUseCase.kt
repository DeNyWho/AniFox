package com.example.anifox.domain.useCase.splash.readOnBoarding

import com.example.anifox.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class ReadOnBoardingUseCase (
    private val repository: Repository
) {
    operator fun invoke(): Flow<Boolean> {
        return repository.readOnBoardingState()
    }
}