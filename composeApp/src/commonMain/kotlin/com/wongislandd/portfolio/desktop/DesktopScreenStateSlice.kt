package com.wongislandd.portfolio.desktop

import com.wongislandd.nexus.events.BackChannelEvent
import com.wongislandd.nexus.viewmodel.ViewModelSlice
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update


data class DesktopScreenState(
    val desktopWidgets: List<Widget>,
    val openFolder: FolderWidget? = null
)

data class DesktopWidgetsUpdate(
    val desktopWidgets: List<Widget>
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
            is DesktopWidgetsUpdate -> {
                _screenState.update {
                    it.copy(desktopWidgets = event.desktopWidgets)
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