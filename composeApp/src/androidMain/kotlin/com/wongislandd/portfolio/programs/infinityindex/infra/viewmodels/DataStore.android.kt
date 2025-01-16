package com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

actual fun createDataStore(context: Any?): DataStore<Preferences> {
    val realContext = requireNotNull(context as Context) {
        "Context must be provided"
    }
    return AppLeveled.getDatastore {
        realContext.filesDir.resolve(DATA_STORE_FILE_NAME).absolutePath
    }
}