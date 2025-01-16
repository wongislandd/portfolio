package com.wongislandd.portfolio.programs.infinityindex.repositories

import com.wongislandd.portfolio.programs.infinityindex.settings.NumberSetting
import com.wongislandd.portfolio.programs.infinityindex.settings.ToggleSetting
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CachingPreferenceRepository(private val dataStoreRepository: DataStoreRepository) {

    private val cachedBooleanPreferences = mutableMapOf<ToggleSetting, Boolean>()
    private val cachedNumberPreferences = mutableMapOf<NumberSetting, Int>()

    init {
        GlobalScope.launch {
            ToggleSetting.entries.forEach { toggleSetting ->
                launch {
                    dataStoreRepository.readBooleanPreference(toggleSetting)
                        .collectLatest { value ->
                            cachedBooleanPreferences[toggleSetting] = value
                        }
                }
            }
            NumberSetting.entries.forEach { numberSetting ->
                launch {
                    dataStoreRepository.readIntPreference(numberSetting).collectLatest { value ->
                        cachedNumberPreferences[numberSetting] = value
                    }
                }
            }
        }
    }

    suspend fun saveBooleanPreference(setting: ToggleSetting, value: Boolean) {
        dataStoreRepository.saveBooleanPreference(setting, value)
    }

    suspend fun saveIntPreference(setting: NumberSetting, value: Int) {
        dataStoreRepository.saveIntPreference(setting, value)
    }

    fun getBooleanPreference(setting: ToggleSetting): Flow<Boolean> {
        return dataStoreRepository.readBooleanPreference(setting)
    }

    fun getIntPreference(setting: NumberSetting): Flow<Int> {
        return dataStoreRepository.readIntPreference(setting)
    }

    fun getCachedBooleanPreference(setting: ToggleSetting): Boolean {
        return cachedBooleanPreferences[setting] ?: setting.defaultValue
    }

    fun getCachedNumberPreference(setting: NumberSetting): Int {
        return cachedNumberPreferences[setting] ?: setting.defaultValue
    }
}