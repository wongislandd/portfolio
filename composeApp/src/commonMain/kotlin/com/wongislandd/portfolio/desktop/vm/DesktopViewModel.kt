package com.wongislandd.portfolio.desktop.vm

import androidx.navigation.NavController
import com.wongislandd.nexus.events.BackChannelEvent
import com.wongislandd.nexus.events.EventBus
import com.wongislandd.nexus.events.UiEvent
import com.wongislandd.nexus.navigation.NavigationSlice
import com.wongislandd.nexus.viewmodel.SliceableViewModel
import com.wongislandd.portfolio.navigation.NavigationItemKey

class DesktopViewModel(
    val desktopScreenStateSlice: DesktopScreenStateSlice,
    val taskbarScreenStateSlice: TaskbarScreenStateSlice,
    val widgetFinderScreenStateSlice: WidgetFinderScreenStateSlice,
    val inspectorScreenStateSlice: InspectorScreenStateSlice,
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
            widgetFinderScreenStateSlice,
            inspectorScreenStateSlice,
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