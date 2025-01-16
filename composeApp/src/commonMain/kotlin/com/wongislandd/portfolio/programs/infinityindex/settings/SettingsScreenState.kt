package com.wongislandd.portfolio.programs.infinityindex.settings

data class SettingsScreenState(
    val toggleSettings: List<SelectableToggleSetting>,
    val numberSettings: List<AdjustableNumberSetting>
)