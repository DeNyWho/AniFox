package com.example.anifox.core.error

sealed interface ResponseError {
    object ClientErrorException: ResponseError
    object IOErrorException: ResponseError
    object ServerErrorException: ResponseError
}