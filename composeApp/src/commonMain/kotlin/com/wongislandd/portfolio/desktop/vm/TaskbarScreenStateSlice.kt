package com.wongislandd.portfolio.desktop.vm

import com.wongislandd.nexus.events.BackChannelEvent
import com.wongislandd.nexus.events.UiEvent
import com.wongislandd.nexus.viewmodel.ViewModelSlice
import com.wongislandd.portfolio.desktop.data.TaskbarWidget
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class TaskbarScreenState(
    val activeWidgets: List<TaskbarWidget> = listOf(),
)

data class ActiveWidgetsUpdate(
    val activeWidgets: List<TaskbarWidget>
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