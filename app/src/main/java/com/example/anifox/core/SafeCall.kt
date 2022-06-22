package com.example.anifox.core

import com.example.anifox.core.error.ResponseError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException

suspend fun safeCall(call: suspend () -> Response<*>): Any? {
    return withContext(Dispatchers.IO) {
        try {
            val response = call.invoke()
            if (response.isSuccessful) {
                response.body()
            } else {
                response.errorBody()
            }
        }
        catch (e:IOException){
            ResponseError.IOErrorException
        }
    }
}