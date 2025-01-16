package com.wongislandd.portfolio.programs.infinityindex.infra.util

import androidx.lifecycle.viewModelScope
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.EventBus
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.UiEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.collectEvents
import kotlinx.coroutines.CoroutineScope

abstract class ViewModelSlice {
    // For managing coroutine scope
    lateinit var sliceScope: CoroutineScope
    // For accessing UI events
    lateinit var uiEvents: EventBus<UiEvent>
    // For accessing backchannel events
    lateinit var backChannelEvents: EventBus<BackChannelEvent>

    fun register(viewModel: SliceableViewModel) {
        sliceScope = viewModel.viewModelScope
        uiEvents = viewModel.uiEventBus
        backChannelEvents = viewModel.backChannelEventBus
        listenForUiEvents()
        listenForBackChannelEvents()
        afterInit()
    }

    open fun afterInit() {}

    private fun listenForUiEvents() {
        sliceScope.collectEvents(uiEvents) {
            handleUiEvent(it)
        }
    }

    private fun listenForBackChannelEvents() {
        sliceScope.collectEvents(backChannelEvents) {
            handleBackChannelEvent(it)
        }
    }

    open fun handleUiEvent(event: UiEvent) { }

    open fun handleBackChannelEvent(event: BackChannelEvent) { }
}