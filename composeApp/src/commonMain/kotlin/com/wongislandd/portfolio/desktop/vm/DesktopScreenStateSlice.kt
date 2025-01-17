package com.wongislandd.portfolio.desktop.vm

import com.wongislandd.nexus.events.BackChannelEvent
import com.wongislandd.nexus.events.UiEvent
import com.wongislandd.nexus.viewmodel.ViewModelSlice
import com.wongislandd.portfolio.desktop.data.FolderWidget
import com.wongislandd.portfolio.desktop.data.Widget
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update


data class DesktopScreenState(
    val desktopWidgets: List<Widget>,
    val openFolder: FolderWidget? = null
)

data object DesktopClickedEvent : UiEvent

data class AvailableWidgetsUpdate(
    val availableWidgets: List<Widget>
) : BackChannelEvent

data class OpenFolderUpdate(
    val folderWidget: FolderWidget?
) : BackChannelEvent

class DesktopScreenStateSlice : ViewModelSlice() {
    private val _screenState = MutableStateFlow(
        DesktopScreenState(desktopWidgets = listOf())
    )
    val screenState: StateFlow<DesktopScreenState> = _screenState

    override fun handleBackChannelEvent(event: BackChannelEvent) {
        when (event) {
            is AvailableWidgetsUpdate -> {
                _screenState.update {
                    it.copy(desktopWidgets = event.availableWidgets)
                }
            }
            is OpenFolderUpdate -> {
                _screenState.update {
                    it.copy(openFolder = event.folderWidget)
                }
            }
        }
    }
}