package com.example.anifox.core

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException


class SafeCall {
    suspend inline operator fun <reified T : Any, reified U : Any> invoke(crossinline call: () -> Response<*>): Any? {
        return withContext(Dispatchers.IO) {
            try {
                val response = call.invoke()
                if (response.isSuccessful) {
                    response.body()
                } else {
                    response.errorBody()
                }
            } catch (e: IOException) {
                e.message
            }
        }
    }
}