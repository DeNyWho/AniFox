package com.example.anifox.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.anifox.data.repository.DataStoreRepository.PreferencesKey.emailKey
import com.example.anifox.data.repository.DataStoreRepository.PreferencesKey.jwtTokenKey
import com.example.anifox.data.repository.DataStoreRepository.PreferencesKey.keyToken
import com.example.anifox.data.repository.DataStoreRepository.PreferencesKey.nameKey
import com.example.anifox.domain.repository.DataStoreRep
import com.example.anifox.util.Constants
import com.example.anifox.util.Constants.EMAIL_KEY
import com.example.anifox.util.Constants.JWT_TOKEN_KEY
import com.example.anifox.util.Constants.NAME_KEY
import com.example.anifox.util.Constants.PREFERENCES_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

class DataStoreRepository (context: Context): DataStoreRep {

    private object PreferencesKey {
        val onBoardingKey = booleanPreferencesKey(name = PREFERENCES_NAME)
        val onLoginKey = booleanPreferencesKey(name = PREFERENCES_NAME)
        val keyToken = stringPreferencesKey(Constants.KEY_TOKEN)
        val jwtTokenKey = stringPreferencesKey(JWT_TOKEN_KEY)
        val nameKey = stringPreferencesKey(NAME_KEY)
        val emailKey = stringPreferencesKey(EMAIL_KEY)
    }

    private val dataStore = context.datastore

    override suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[keyToken] = token
        }
    }

    override suspend fun getJwtToken(): String? {
        val preferences = dataStore.data.first()
        return preferences[jwtTokenKey]
    }

    override suspend fun getCurrentUserName(): String? {
        val preferences = dataStore.data.first()
        return preferences[nameKey]
    }

    override suspend fun getCurrentUserEmail(): String? {
        val preferences = dataStore.data.first()
        return preferences[emailKey]
    }

    override suspend fun logout(){
        dataStore.edit {
            it.clear()
        }
    }

    override suspend fun updateSession(token: String, name: String, email: String) {
        dataStore.edit { preferences ->
            preferences[jwtTokenKey] = token
            preferences[nameKey] = name
            preferences[emailKey] = email
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