package com.example.anifox.di

import com.example.anifox.core.Endpoints.BASE_URL
import com.example.anifox.core.SafeCall
import com.example.anifox.data.repository.Repository
import com.example.anifox.domain.useCase.splash.ReadOnBoardingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {

        return OkHttpClient().newBuilder()
            .followRedirects(true)
            .followSslRedirects(false)
            .build()
    }

    @Provides
    @Singleton
    fun provideCall(): SafeCall {
        return SafeCall()
    }
}