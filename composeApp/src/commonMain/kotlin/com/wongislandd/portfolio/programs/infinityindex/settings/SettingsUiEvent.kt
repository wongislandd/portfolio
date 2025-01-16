package com.wongislandd.portfolio.programs.infinityindex.settings

import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.UiEvent

sealed class SettingsUiEvent : UiEvent {
    data class ToggledSetting(val toggleSetting: ToggleSetting, val shouldEnable: Boolean) : SettingsUiEvent()
    data class NumberSettingChanged(val setting: NumberSetting, val newValue: Int) : SettingsUiEvent()
}