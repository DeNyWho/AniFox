package com.example.anifox.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {
    suspend fun saveOnBoardingState(completed: Boolean)
    fun readOnBoardingState(): Flow<Boolean>
    suspend fun saveLoginState(completed: Boolean)
    fun readOnLoginState(): Flow<Boolean>
}