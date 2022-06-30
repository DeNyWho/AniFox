package com.example.anifox.di

import android.content.Context
import com.example.anifox.data.dataSource.AnimeDataSource
import com.example.anifox.data.remote.api.AnimeApi
import com.example.anifox.data.remote.api.UserApi
import com.example.anifox.data.repository.AnimeRepository
import com.example.anifox.data.repository.DataStoreOperationsImpl
import com.example.anifox.data.repository.DataStoreRepository
import com.example.anifox.domain.repository.DataStoreOperations
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

//    @Provides
//    @Singleton
//    fun provideAuthRepository(
//        userApi: UserApi
//    ) = AuthRepository(userApi)

    @Provides
    @Singleton
    fun provideDataStoreRepository(
        dataStore: DataStoreOperations
    ) = DataStoreRepository(dataStore)

    @Provides
    @Singleton
    fun provideAnimeRepository(
        animeApi: AnimeApi,
        animeDataSource: AnimeDataSource.Factory
    ) = AnimeRepository(animeApi, animeDataSource)

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



}