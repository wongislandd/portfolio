package com.wongislandd.portfolio.programs.infinityindex.settings

import com.wongislandd.portfolio.programs.infinityindex.infra.util.ViewModelSlice
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.UiEvent
import com.wongislandd.portfolio.programs.infinityindex.repositories.CachingPreferenceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsScreenStateSlice(
    private val cachingPreferenceRepository: CachingPreferenceRepository
) : ViewModelSlice() {

    private val _screenState: MutableStateFlow<SettingsScreenState>

    val screenState: StateFlow<SettingsScreenState>

    init {
        val toggleSettings = ToggleSetting.entries.map {
            val isSettingEnabled = cachingPreferenceRepository.getCachedBooleanPreference(it)
            SelectableToggleSetting(
                it,
                currentValue = isSettingEnabled
            )
        }
        val numberSettings = NumberSetting.entries.map {
            val currentValue = cachingPreferenceRepository.getCachedNumberPreference(it)
            AdjustableNumberSetting(
                it,
                currentValue = currentValue
            )
        }
        _screenState = MutableStateFlow(SettingsScreenState(toggleSettings, numberSettings))
        screenState = _screenState
    }

    override fun afterInit() {
        super.afterInit()
        listenForSettingChanges()
    }

    private fun listenForSettingChanges() {
        sliceScope.launch {
            cachingPreferenceRepository.getBooleanPreference(ToggleSetting.DIGITALLY_AVAILABLE)
                .collectLatest { isDigitallyAvailableEnabled ->
                    _screenState.update {
                        it.copy(
                            toggleSettings = it.toggleSettings.map { setting ->
                                if (setting.setting == ToggleSetting.DIGITALLY_AVAILABLE) {
                                    setting.copy(currentValue = isDigitallyAvailableEnabled)
                                } else {
                                    setting
                                }
                            }
                        )
                    }
                }
        }
        sliceScope.launch {
            cachingPreferenceRepository.getBooleanPreference(ToggleSetting.VARIANTS)
                .collectLatest { isVariantsEnabled ->
                    _screenState.update {
                        it.copy(
                            toggleSettings = it.toggleSettings.map { setting ->
                                if (setting.setting == ToggleSetting.VARIANTS) {
                                    setting.copy(currentValue = isVariantsEnabled)
                                } else {
                                    setting
                                }
                            }
                        )
                    }
                }
        }
        sliceScope.launch {
            cachingPreferenceRepository.getIntPreference(NumberSetting.LOOK_AHEAD_DAYS)
                .onEach { lookAheadDaysChanged ->
                    _screenState.update {
                        it.copy(
                            numberSettings = it.numberSettings.map { setting ->
                                if (setting.setting == NumberSetting.LOOK_AHEAD_DAYS) {
                                    setting.copy(currentValue = lookAheadDaysChanged)
                                } else {
                                    setting
                                }
                            }
                        )
                    }

                }

        }
    }

    override fun handleUiEvent(event: UiEvent) {
        super.handleUiEvent(event)
        when (event) {
            is SettingsUiEvent.ToggledSetting -> {
                adjustToggleSetting(event.toggleSetting, event.shouldEnable)
            }
            is SettingsUiEvent.NumberSettingChanged -> {
                adjustNumberSetting(event.setting, event.newValue)
            }

        }
    }

    private fun adjustToggleSetting(toggleSetting: ToggleSetting, shouldEnable: Boolean) {
        sliceScope.launch {
            cachingPreferenceRepository.saveBooleanPreference(toggleSetting, shouldEnable)
            _screenState.update {
                val newList = it.toggleSettings.map { existingSetting ->
                    if (existingSetting.setting == toggleSetting) {
                        existingSetting.copy(currentValue = shouldEnable)
                    } else {
                        existingSetting
                    }
                }
                it.copy(toggleSettings = newList)
            }
        }
    }

    private fun adjustNumberSetting(setting: NumberSetting, newValue: Int) {
        sliceScope.launch {
            cachingPreferenceRepository.saveIntPreference(setting, newValue)
            _screenState.update {
                val newList = it.numberSettings.map { existingSetting ->
                    if (existingSetting.setting == setting) {
                        existingSetting.copy(currentValue = newValue)
                    } else {
                        existingSetting
                    }
                }
                it.copy(numberSettings = newList)
            }
        }
    }
}