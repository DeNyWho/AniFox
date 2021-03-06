package com.example.anifox.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.anifox.data.repository.DataStoreOperationsImpl.PreferencesKey.keyToken
import com.example.anifox.data.repository.DataStoreOperationsImpl.PreferencesKey.refreshToken
import com.example.anifox.domain.repository.DataStoreOperations
import com.example.anifox.util.Constants
import com.example.anifox.util.Constants.PREFERENCES_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

class DataStoreOperationsImpl (context: Context): DataStoreOperations {

    private object PreferencesKey {
        val onBoardingKey = booleanPreferencesKey(name = PREFERENCES_NAME)
        val onLoginKey = booleanPreferencesKey(name = PREFERENCES_NAME)
        val keyToken = stringPreferencesKey(Constants.KEY_TOKEN)
        val refreshToken = stringPreferencesKey(Constants.REFRESH_TOKEN)
    }

    private val dataStore = context.datastore

    suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[keyToken] = token
        }
    }

    suspend fun saveRefreshToken(token: String) {
        dataStore.edit { preferences ->
            preferences[refreshToken] = token
        }
    }

    override suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.onBoardingKey] = completed
        }
    }

    override fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onBoardingState = preferences[PreferencesKey.onBoardingKey] ?: false
                onBoardingState
            }
    }

    override suspend fun saveLoginState(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.onLoginKey] = completed
        }
    }

    override fun readOnLoginState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onLoginState = preferences[PreferencesKey.onLoginKey] ?: false
                onLoginState
            }
    }
}