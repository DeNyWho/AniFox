package com.example.anifox.di

import com.example.anifox.BuildConfig
import com.example.anifox.core.interceptors.UserAgentInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
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

    @UserAgentInterceptorOkHttpClient
    @Singleton
    @Provides
    fun providesUserAgentInterceptor(): Interceptor = UserAgentInterceptor()

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(providesUserAgentInterceptor())
            .followRedirects(true)
            .followSslRedirects(false)
            .build()
    }

}