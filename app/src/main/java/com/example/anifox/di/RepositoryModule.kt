package com.example.anifox.di

import android.content.Context
import com.example.anifox.data.dataSource.AnimeDataSource
import com.example.anifox.data.remote.api.MainApi
import com.example.anifox.data.remote.api.UserApi
import com.example.anifox.data.repository.DataStoreRepository
import com.example.anifox.data.repository.MangaRepository
import com.example.anifox.data.repository.UserRepository
import com.example.anifox.domain.repository.DataStoreRep
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMangaRepository(
        mainApi: MainApi,
        animeDataSource: AnimeDataSource.Factory,
    ) = MangaRepository(mainApi, animeDataSource)

    @Provides
    @Singleton
    fun provideUserRepository(
        userApi: UserApi
    ) = UserRepository(userApi)

    @Provides
    fun provideMainApi(@Named("RetrofitMainApi") retrofit: Retrofit) : MainApi {
        return retrofit.create(MainApi::class.java)
    }

    @Provides
    fun provideUserApi(@Named("RetrofitMainApi") retrofit: Retrofit) : UserApi {
        return retrofit.create(UserApi::class.java)
    }

    @Provides
    @Singleton
    fun providesApplicationContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideDataStoreOperations(
        @ApplicationContext context: Context
    ): DataStoreRep {
        return DataStoreRepository(context = context)
    }

    @Provides
    @Singleton
    fun provideDataStoreOperationsImpl(
        @ApplicationContext context: Context
    ): DataStoreRepository {
        return DataStoreRepository(context = context)
    }



}