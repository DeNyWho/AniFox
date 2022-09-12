package com.example.anifox.core.interceptors

import com.example.anifox.BuildConfig
import com.example.anifox.util.Constants
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor


class UserAgentInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader("User-Agent", Constants.APP_NAME )
            .build()
        return chain.proceed(newRequest)
    }
}

class LoggingInterceptor {
    fun log(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
        return loggingInterceptor
    }
}
