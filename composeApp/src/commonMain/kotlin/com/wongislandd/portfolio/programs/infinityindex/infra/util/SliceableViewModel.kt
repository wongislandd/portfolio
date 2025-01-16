package com.wongislandd.portfolio.programs.infinityindex.infra.util

import androidx.lifecycle.ViewModel
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.EventBus
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.UiEvent

abstract class SliceableViewModel(
    val uiEventBus: EventBus<UiEvent>,
    val backChannelEventBus: EventBus<BackChannelEvent>
) : ViewModel() {

    fun registerSlice(viewModelSlice: ViewModelSlice) {
        viewModelSlice.register(this)
    }

    override fun onCleared() {
        super.onCleared()
    }
}