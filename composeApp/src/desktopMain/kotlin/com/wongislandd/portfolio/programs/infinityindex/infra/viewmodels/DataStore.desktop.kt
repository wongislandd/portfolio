package com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

actual fun createDataStore(context: Any?): DataStore<Preferences> {
    return AppLeveled.getDatastore { DATA_STORE_FILE_NAME }
}