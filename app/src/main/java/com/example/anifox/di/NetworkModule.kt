package com.example.anifox.di

import com.example.anifox.core.DefaultDispatchers
import com.example.anifox.core.DispatchersProvider
import com.example.anifox.core.SafeCall
import com.example.anifox.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideDispatchersProvider(): DispatchersProvider {
        return DefaultDispatchers(
            default = Dispatchers.Default,
            main = Dispatchers.Main,
            io = Dispatchers.IO,
            mainImmediate = Dispatchers.Main,
            unconfined = Dispatchers.Unconfined
        )
    }

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