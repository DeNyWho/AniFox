package com.example.anifox.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.anifox.util.Constants.KEY_STATE
import com.example.anifox.util.Constants.KEY_TOKEN
import com.example.anifox.util.Constants.REFRESH_TOKEN
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.json.JSONException

class SessionManager(val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("session_manager")
    private val authStateScope = CoroutineScope(Dispatchers.IO)
    private val keyState = stringPreferencesKey(KEY_STATE)
    private val keyToken = stringPreferencesKey(KEY_TOKEN)
    private val refreshToken = stringPreferencesKey(REFRESH_TOKEN)


    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[keyToken] = token
        }
    }

    suspend fun saveRefreshToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[refreshToken] = token
        }
    }


    suspend fun logout(){
        context.dataStore.edit {
            it.clear()
        }
    }

}