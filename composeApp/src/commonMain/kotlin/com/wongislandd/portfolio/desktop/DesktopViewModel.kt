package com.wongislandd.portfolio.desktop

import com.wongislandd.nexus.events.BackChannelEvent
import com.wongislandd.nexus.events.EventBus
import com.wongislandd.nexus.events.UiEvent
import com.wongislandd.nexus.viewmodel.SliceableViewModel

class DesktopViewModel(
    val desktopScreenStateSlice: DesktopScreenStateSlice,
    val taskbarScreenStateSlice: TaskbarScreenStateSlice,
    activeWidgetsSlice: ActiveWidgetsSlice,
    widgetProviderSlice: WidgetProviderSlice,
    uiEventBus: EventBus<UiEvent>,
    backChannelEventBus: EventBus<BackChannelEvent>
): SliceableViewModel(uiEventBus, backChannelEventBus) {

    init {
        registerSlices(
            desktopScreenStateSlice,
            taskbarScreenStateSlice,
            activeWidgetsSlice,
            widgetProviderSlice
        )
    }
}