package com.wongislandd.portfolio.desktop

import com.wongislandd.nexus.events.BackChannelEvent
import com.wongislandd.nexus.viewmodel.ViewModelSlice
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class TaskbarScreenState(
    val activeWidgets: List<SelectableWidget> = listOf(),
)

data class ActiveWidgetsUpdate(
    val activeWidgets: List<SelectableWidget>
) : BackChannelEvent

class TaskbarScreenStateSlice : ViewModelSlice() {

    private val _screenState = MutableStateFlow(TaskbarScreenState())
    val screenState: StateFlow<TaskbarScreenState>
        get() = _screenState

    override fun handleBackChannelEvent(event: BackChannelEvent) {
        when (event) {
            is ActiveWidgetsUpdate -> {
                _screenState.update {
                    it.copy(activeWidgets = event.activeWidgets)
                }
            }
        }
    }
}