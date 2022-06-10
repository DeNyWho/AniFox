package com.example.anifox.domain.useCase

import com.example.anifox.domain.useCase.splash.readOnBoarding.ReadOnBoardingUseCase
import com.example.anifox.domain.useCase.splash.saveOnBoarding.SaveOnBoardingUseCase

data class UseCases (
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
)