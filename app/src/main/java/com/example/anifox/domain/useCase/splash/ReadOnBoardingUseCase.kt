package com.example.anifox.domain.useCase.splash

import com.example.anifox.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadOnBoardingUseCase @Inject constructor (
    private val repository: Repository
) {
    operator fun invoke(): Flow<Boolean> {
        return repository.readOnBoardingState()
    }
}