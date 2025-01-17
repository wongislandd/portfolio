package com.wongislandd.portfolio.desktop.vm

import com.wongislandd.nexus.events.BackChannelEvent
import com.wongislandd.nexus.events.Event
import com.wongislandd.nexus.events.UiEvent
import com.wongislandd.nexus.viewmodel.ViewModelSlice
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data object InspectorEventsLogClearedEvent : UiEvent

private const val MAX_EVENTS = 150

class InspectorScreenStateSlice : ViewModelSlice() {

    private val _allEventsFlow: MutableStateFlow<List<Event>> = MutableStateFlow(listOf())
    val events: StateFlow<List<Event>>
        get() = _allEventsFlow

    override fun handleUiEvent(event: UiEvent) {
        updateEventsFlow(event)
    }

    override fun handleBackChannelEvent(event: BackChannelEvent) {
        updateEventsFlow(event)
    }

    private fun updateEventsFlow(newEvent: Event) {
        if (newEvent is InspectorEventsLogClearedEvent) {
            _allEventsFlow.update {
                emptyList()
            }
        }
        _allEventsFlow.update {
            val result = listOf(newEvent) + it
            if (result.size == MAX_EVENTS) {
                result.dropLast(1)
            } else {
                result
            }
        }
    }
}