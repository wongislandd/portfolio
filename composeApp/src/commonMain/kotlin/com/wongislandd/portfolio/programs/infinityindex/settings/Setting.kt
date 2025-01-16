package com.wongislandd.portfolio.programs.infinityindex.settings

import androidx.datastore.preferences.core.Preferences

interface Setting<T : Any> {
    val displayName: String
    val description: String
    val key: Preferences.Key<T>
    val defaultValue: T
}