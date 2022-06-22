package com.example.anifox.di

import android.content.Context
import com.example.anifox.data.remote.api.UserService
import com.example.anifox.data.repository.AuthRepository
import com.example.anifox.data.repository.DataStoreOperationsImpl
import com.example.anifox.data.repository.Repository
import com.example.anifox.domain.repository.DataStoreOperations
import com.example.anifox.domain.useCase.splash.ReadOnBoardingUseCase
import com.example.anifox.domain.useCase.splash.SaveOnBoardingUseCase
import com.example.anifox.util.SessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideSessionManager(
        @ApplicationContext context: Context
    ) = SessionManager(context = context)

    @Provides
    @Singleton
    fun provideAuthRepository(
        userService: UserService
    ) = AuthRepository(userService)

    @Provides
    fun provideUserService(retrofit: Retrofit) : UserService {
        return retrofit.create(UserService::class.java)
    }
    @Provides
    @Singleton
    fun provideDataStoreOperations(
        @ApplicationContext context: Context
    ): DataStoreOperations {
        return DataStoreOperationsImpl(context = context)
    }

    @Provides
    fun readOnBoardingUseCase(repository: Repository): ReadOnBoardingUseCase {
        return ReadOnBoardingUseCase(repository)
    }

    @Provides
    fun saveOnBoardingUseCase(repository: Repository): SaveOnBoardingUseCase {
        return SaveOnBoardingUseCase(repository)
    }


}