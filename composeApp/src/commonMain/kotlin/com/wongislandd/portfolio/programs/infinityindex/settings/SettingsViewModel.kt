package com.wongislandd.portfolio.programs.infinityindex.settings

import com.wongislandd.portfolio.programs.infinityindex.infra.util.SliceableViewModel
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.EventBus
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.UiEvent

class SettingsViewModel(
    val settingsScreenStateSlice: SettingsScreenStateSlice,
    uiEventBus: EventBus<UiEvent>,
    backChannelEventBus: EventBus<BackChannelEvent>
) : SliceableViewModel(
    uiEventBus,
    backChannelEventBus
) {

    init {
        registerSlice(settingsScreenStateSlice)
    }
}