package com.wongislandd.portfolio.programs.infinityindex.settings

data class SelectableToggleSetting(
    val setting: ToggleSetting,
    val currentValue: Boolean,
)

data class AdjustableNumberSetting(
    val setting: NumberSetting,
    val currentValue: Int,
)