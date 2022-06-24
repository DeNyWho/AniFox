package com.example.anifox.di

import android.content.Context
import com.example.anifox.data.remote.api.AnimeApi
import com.example.anifox.data.remote.api.UserApi
import com.example.anifox.data.repository.AnimeRepository
import com.example.anifox.data.repository.AuthRepository
import com.example.anifox.data.repository.DataStoreOperationsImpl
import com.example.anifox.data.repository.Repository
import com.example.anifox.domain.repository.DataStoreOperations
import com.example.anifox.domain.useCase.home.GetAnimeByPopularReview
import com.example.anifox.domain.useCase.home.GetAnimesUseCase
import com.example.anifox.domain.useCase.splash.ReadOnBoardingUseCase
import com.example.anifox.domain.useCase.splash.SaveOnBoardingUseCase
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
    fun provideAuthRepository(
        userApi: UserApi
    ) = AuthRepository(userApi)

    @Provides
    @Singleton
    fun provideAnimeRepository(
        animeApi: AnimeApi
    ) = AnimeRepository(animeApi)

    @Provides
    fun provideUserService(retrofit: Retrofit) : UserApi {
        return retrofit.create(UserApi::class.java)
    }

    @Provides
    fun provideAnimeApi(retrofit: Retrofit) : AnimeApi {
        return retrofit.create(AnimeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDataStoreOperations(
        @ApplicationContext context: Context
    ): DataStoreOperations {
        return DataStoreOperationsImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideDataStoreOperationsImpl(
        @ApplicationContext context: Context
    ): DataStoreOperationsImpl {
        return DataStoreOperationsImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideGetAnimeByPopularReview(
        repository: AnimeRepository
    ): GetAnimeByPopularReview {
        return GetAnimeByPopularReview(repository)
    }

    @Provides
    @Singleton
    fun provideGetAnime(
        repository: AnimeRepository
    ): GetAnimesUseCase {
        return GetAnimesUseCase(repository)
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