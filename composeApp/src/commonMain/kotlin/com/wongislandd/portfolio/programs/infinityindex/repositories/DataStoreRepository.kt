package com.wongislandd.portfolio.programs.infinityindex.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.wongislandd.portfolio.programs.infinityindex.settings.NumberSetting
import com.wongislandd.portfolio.programs.infinityindex.settings.ToggleSetting
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreRepository(
    private val dataStore: DataStore<Preferences>
) {

    suspend fun saveBooleanPreference(setting: ToggleSetting, value: Boolean) {
        dataStore.edit { preferences ->
            preferences[setting.key] = value
        }
    }

    fun readBooleanPreference(setting: ToggleSetting): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[setting.key] ?: setting.defaultValue
        }
    }

    suspend fun saveIntPreference(setting: NumberSetting, value: Int) {
        dataStore.edit { preferences ->
            preferences[setting.key] = value
        }
    }

    fun readIntPreference(setting: NumberSetting): Flow<Int> {
        return dataStore.data.map { preferences ->
            preferences[setting.key] ?: setting.defaultValue
        }
    }
}