package com.example.anifox.di

import android.content.Context
import com.example.anifox.core.DispatchersProvider
import com.example.anifox.data.repository.DataStoreOperationsImpl
import com.example.anifox.data.repository.Repository
import com.example.anifox.domain.repository.DataStoreOperations
import com.example.anifox.domain.useCase.UseCases
import com.example.anifox.domain.useCase.splash.readOnBoarding.ReadOnBoardingUseCase
import com.example.anifox.domain.useCase.splash.saveOnBoarding.SaveOnBoardingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(
        @ApplicationContext context: Context
    ): DataStoreOperations {
        return DataStoreOperationsImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideUseCases(
        repository: Repository,
        dispatchers: DispatchersProvider
    ): UseCases {
        return UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository = repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository = repository),
        )

    }
}