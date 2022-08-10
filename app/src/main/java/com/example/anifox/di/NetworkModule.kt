package com.example.anifox.di

import com.example.anifox.BuildConfig.BASE_URL_Main_API
import com.example.anifox.core.interceptors.UserAgentInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    @Named("RetrofitMainApi")
    fun provideMainRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_Main_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @UserAgentInterceptorOkHttpClient
    @Singleton
    @Provides
    fun providesUserAgentInterceptor(): Interceptor = UserAgentInterceptor()

    @Provides
    @Singleton
    fun provideHttpClient(@UserAgentInterceptorOkHttpClient headerAgent: Interceptor): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(headerAgent)
            .followRedirects(true)
            .followSslRedirects(false)
            .build()
    }

}