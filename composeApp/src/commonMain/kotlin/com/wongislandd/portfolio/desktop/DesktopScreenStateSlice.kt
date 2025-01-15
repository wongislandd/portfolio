package com.wongislandd.portfolio.desktop

import com.wongislandd.nexus.events.BackChannelEvent
import com.wongislandd.nexus.viewmodel.ViewModelSlice
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class DesktopScreenState(val availableWidgets: List<Widget>)

data class AvailableWidgetsUpdate(val availableWidgets: List<Widget>): BackChannelEvent

class DesktopScreenStateSlice : ViewModelSlice() {
    private val _screenState = MutableStateFlow(
        DesktopScreenState(availableWidgets = listOf())
    )
    val screenState: StateFlow<DesktopScreenState> = _screenState

    override fun handleBackChannelEvent(event: BackChannelEvent) {
        when (event) {
            is AvailableWidgetsUpdate -> {
                _screenState.update {
                    it.copy(availableWidgets = event.availableWidgets)
                }
            }
        }
    }
}