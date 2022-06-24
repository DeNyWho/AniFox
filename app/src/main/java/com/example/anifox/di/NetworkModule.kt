package com.example.anifox.di

import com.example.anifox.BuildConfig
import com.example.anifox.util.Constants
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
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {

        return OkHttpClient().newBuilder()
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("User-Agent", Constants.APP_NAME )
                    .build()
                chain.proceed(newRequest)
            }
            .followRedirects(true)
            .followSslRedirects(false)
            .build()
    }

}