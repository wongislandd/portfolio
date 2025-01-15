package com.wongislandd.portfolio

import androidx.navigation.NavController
import com.wongislandd.nexus.events.BackChannelEvent
import com.wongislandd.nexus.events.EventBus
import com.wongislandd.nexus.events.UiEvent
import com.wongislandd.nexus.navigation.NavigationSlice
import com.wongislandd.nexus.viewmodel.SliceableViewModel
import com.wongislandd.portfolio.desktop.WidgetManagementSlice
import com.wongislandd.portfolio.desktop.DesktopScreenStateSlice
import com.wongislandd.portfolio.desktop.TaskbarScreenStateSlice
import com.wongislandd.portfolio.desktop.WidgetProviderSlice
import com.wongislandd.portfolio.navigation.NavigationItemKey

class AppViewModel(
    val desktopScreenStateSlice: DesktopScreenStateSlice,
    val taskbarScreenStateSlice: TaskbarScreenStateSlice,
    private val navigationSlice: NavigationSlice,
    widgetManagementSlice: WidgetManagementSlice,
    widgetProviderSlice: WidgetProviderSlice,
    uiEventBus: EventBus<UiEvent>,
    backChannelEventBus: EventBus<BackChannelEvent>
) : SliceableViewModel(uiEventBus, backChannelEventBus) {

    init {
        registerSlices(
            navigationSlice, desktopScreenStateSlice, taskbarScreenStateSlice,
            widgetManagementSlice, widgetProviderSlice
        )
    }

    fun navigate(
        navigationController: NavController,
        navigationKey: NavigationItemKey,
        args: Map<String, Any?> = emptyMap(),
        removeSelfFromStack: Boolean = false
    ) {
        navigationSlice.navigationHelper.navigate(
            navigationController,
            navigationKey.name,
            args,
            removeSelfFromStack
        )
    }
}