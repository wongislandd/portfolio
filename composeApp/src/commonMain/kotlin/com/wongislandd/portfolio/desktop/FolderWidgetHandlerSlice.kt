package com.wongislandd.portfolio.desktop

import com.wongislandd.nexus.events.UiEvent
import kotlinx.coroutines.launch

data object CloseFolderEvent: UiEvent

class FolderWidgetHandlerSlice: WidgetClickHandlerSlice<FolderWidget>() {

    override fun handleUiEvent(event: UiEvent) {
        super.handleUiEvent(event)
        when (event) {
            is CloseFolderEvent -> {
                sliceScope.launch {
                    backChannelEvents.sendEvent(OpenFolderUpdate(null))
                }
            }
        }

    }

    override fun handleWidgetClicked(clickedWidget: FolderWidget) {
        sliceScope.launch {
            backChannelEvents.sendEvent(OpenFolderUpdate(clickedWidget))
        }
    }
}