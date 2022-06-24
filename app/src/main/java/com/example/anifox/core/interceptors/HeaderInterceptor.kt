package com.example.anifox.core.interceptors

import com.example.anifox.util.Constants
import okhttp3.Interceptor
import okhttp3.Response


class UserAgentInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader("User-Agent", Constants.APP_NAME )
            .build()
        return chain.proceed(newRequest)
    }
}