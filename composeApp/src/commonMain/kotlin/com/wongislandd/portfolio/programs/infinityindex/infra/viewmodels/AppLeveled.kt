package com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import io.ktor.utils.io.InternalAPI
import io.ktor.utils.io.locks.SynchronizedObject
import io.ktor.utils.io.locks.synchronized
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import okio.Path.Companion.toPath

internal const val DATA_STORE_FILE_NAME = "prefs.preferences_pb"

@OptIn(InternalAPI::class)
object AppLeveled {

    private val _attributionText: MutableStateFlow<String?> = MutableStateFlow(null)
    val attributionText: StateFlow<String?> = _attributionText

    private lateinit var dataStore: DataStore<Preferences>
    private val lock = SynchronizedObject()

    fun updateAttributionText(attributionText: String?) {
        _attributionText.update { attributionText }
    }

    fun getDatastore(producePath: () -> String): DataStore<Preferences> {
        return synchronized(lock) {
            if (::dataStore.isInitialized) {
                dataStore
            } else {
                PreferenceDataStoreFactory.createWithPath {
                    producePath().toPath()
                }.also { dataStore = it}
            }
        }
    }
}