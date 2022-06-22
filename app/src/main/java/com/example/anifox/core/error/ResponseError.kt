package com.example.anifox.core.error

sealed class ResponseError: Exception(){
    object ClientErrorException: ResponseError()
    object IOErrorException: ResponseError()
    object ServerErrorException: ResponseError()
}