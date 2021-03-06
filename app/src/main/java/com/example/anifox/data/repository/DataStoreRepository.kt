package com.example.anifox.data.repository

import com.example.anifox.domain.repository.DataStoreOperations
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataStoreRepository @Inject constructor(
    private val dataStore: DataStoreOperations
) {

    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.saveOnBoardingState(completed = completed)
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.readOnBoardingState()
    }

    suspend fun saveOnLoginState(completed: Boolean) {
        dataStore.saveLoginState(completed = completed)
    }

    fun readOnLoginState(): Flow<Boolean> {
        return dataStore.readOnLoginState()
    }
}