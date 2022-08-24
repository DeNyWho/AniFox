package com.example.anifox.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreRep {
    suspend fun saveOnBoardingState(completed: Boolean)
    fun readOnBoardingState(): Flow<Boolean>
    suspend fun saveLoginState(completed: Boolean)
    fun readOnLoginState(): Flow<Boolean>
    suspend fun saveToken(token: String)
    suspend fun getCurrentUserEmail(): String?
    suspend fun getCurrentUserName(): String?
    suspend fun getJwtToken(): String?
    suspend fun logout()
    suspend fun updateSession(token: String, name: String, email: String)
}