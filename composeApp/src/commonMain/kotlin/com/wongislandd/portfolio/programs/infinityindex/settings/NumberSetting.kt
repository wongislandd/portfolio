package com.wongislandd.portfolio.programs.infinityindex.settings

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import com.wongislandd.portfolio.programs.infinityindex.ComicConstants

enum class NumberSetting(
    override val displayName: String,
    override val description: String,
    override val key: Preferences.Key<Int>,
    override val defaultValue: Int,
) : Setting<Int> {
    LOOK_AHEAD_DAYS(
        "Look Forward Days",
        "Comics served will be available at most this many days in the future.",
        intPreferencesKey("numberOfComicsPerPage"),
        ComicConstants.DEFAULT_LOOK_AHEAD_DAYS
    )
}