package com.example.anifox.di

import android.content.Context
import com.example.anifox.data.dataSource.AnimeDataSource
import com.example.anifox.data.remote.api.AuthApi
import com.example.anifox.data.remote.api.MangaApi
import com.example.anifox.data.repository.AuthRepository
import com.example.anifox.data.repository.DataStoreRepository
import com.example.anifox.data.repository.MangaRepository
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
        mangaApi: MangaApi,
        animeDataSource: AnimeDataSource.Factory,
    ) = MangaRepository(mangaApi, animeDataSource)

    @Provides
    @Singleton
    fun provideAuthRepository(
        authApi: AuthApi
    ) = AuthRepository(authApi)

    @Provides
    fun provideMangaApi(@Named("RetrofitMainApi") retrofit: Retrofit) : MangaApi {
        return retrofit.create(MangaApi::class.java)
    }

    @Provides
    fun provideAuthApi(@Named("RetrofitMainApi") retrofit: Retrofit) : AuthApi {
        return retrofit.create(AuthApi::class.java)
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