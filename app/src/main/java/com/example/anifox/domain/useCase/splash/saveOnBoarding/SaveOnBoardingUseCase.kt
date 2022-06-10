package com.example.anifox.domain.useCase.splash.saveOnBoarding

import com.example.anifox.data.repository.Repository

class SaveOnBoardingUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(completed: Boolean) {
        repository.saveOnBoardingState(completed = completed)
    }
}