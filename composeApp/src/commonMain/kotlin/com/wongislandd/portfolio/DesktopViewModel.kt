package com.wongislandd.portfolio

import androidx.navigation.NavController
import com.wongislandd.nexus.events.BackChannelEvent
import com.wongislandd.nexus.events.EventBus
import com.wongislandd.nexus.events.UiEvent
import com.wongislandd.nexus.navigation.NavigationSlice
import com.wongislandd.nexus.viewmodel.SliceableViewModel
import com.wongislandd.portfolio.desktop.ProgramWidgetManagementSlice
import com.wongislandd.portfolio.desktop.DesktopScreenStateSlice
import com.wongislandd.portfolio.desktop.FolderWidgetHandlerSlice
import com.wongislandd.portfolio.desktop.LinkWidgetHandlerSlice
import com.wongislandd.portfolio.desktop.TaskbarScreenStateSlice
import com.wongislandd.portfolio.desktop.WidgetProviderSlice
import com.wongislandd.portfolio.navigation.NavigationItemKey

class DesktopViewModel(
    val desktopScreenStateSlice: DesktopScreenStateSlice,
    val taskbarScreenStateSlice: TaskbarScreenStateSlice,
    private val navigationSlice: NavigationSlice,
    programWidgetManagementSlice: ProgramWidgetManagementSlice,
    linkWidgetHandlerSlice: LinkWidgetHandlerSlice,
    folderWidgetHandlerSlice: FolderWidgetHandlerSlice,
    widgetProviderSlice: WidgetProviderSlice,
    uiEventBus: EventBus<UiEvent>,
    backChannelEventBus: EventBus<BackChannelEvent>
) : SliceableViewModel(uiEventBus, backChannelEventBus) {

    init {
        registerSlices(
            navigationSlice, desktopScreenStateSlice, taskbarScreenStateSlice,
            linkWidgetHandlerSlice, folderWidgetHandlerSlice,
            programWidgetManagementSlice, widgetProviderSlice
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