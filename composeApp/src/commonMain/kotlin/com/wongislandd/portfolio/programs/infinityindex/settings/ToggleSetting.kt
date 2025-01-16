package com.wongislandd.portfolio.programs.infinityindex.settings

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey

enum class ToggleSetting(
    override val displayName: String,
    override val description: String,
    override val key: Preferences.Key<Boolean>,
    override val defaultValue: Boolean
) : Setting<Boolean> {
    VARIANTS(
        "Display Variants",
        "Variants are copies of a comic, with different cover art.",
        booleanPreferencesKey("variants"),
        false
    ),
    DIGITALLY_AVAILABLE(
        "Limit Results to Digitally Available",
        "Enabling this will limit comic results to those which are available to read online.",
        booleanPreferencesKey("digitallyAvailable"),
        false
    )
}