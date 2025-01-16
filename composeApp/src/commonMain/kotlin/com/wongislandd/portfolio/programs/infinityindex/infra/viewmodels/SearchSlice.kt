package com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels

import com.wongislandd.portfolio.programs.infinityindex.infra.PagingBackChannelEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.ListUiEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.util.ViewModelSlice
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.UiEvent
import kotlinx.coroutines.launch

class SearchSlice : ViewModelSlice() {

    override fun handleUiEvent(event: UiEvent) {
        when (event) {
            is ListUiEvent.SearchClicked -> {
                sliceScope.launch {
                    backChannelEvents.sendEvent(PagingBackChannelEvent.UpdateSearchBoxVisibility(true))
                }
            }
            is ListUiEvent.SetPendingSearchQuery -> {
                sliceScope.launch {
                    backChannelEvents.sendEvent(PagingBackChannelEvent.UpdatePendingSearchQuery(event.query))
                }
            }
            is ListUiEvent.SubmitSearchQuery -> {
                sliceScope.launch {
                    backChannelEvents.sendEvent(PagingBackChannelEvent.SubmitSearchQuery(event.query))
                    backChannelEvents.sendEvent(PagingBackChannelEvent.UpdateSearchBoxVisibility(false))
                }
            }
            is ListUiEvent.ClearSearchQuery -> {
                sliceScope.launch {
                    backChannelEvents.sendEvent(PagingBackChannelEvent.UpdatePendingSearchQuery(""))
                    backChannelEvents.sendEvent(PagingBackChannelEvent.SubmitSearchQuery(""))
                    backChannelEvents.sendEvent(PagingBackChannelEvent.UpdateSearchBoxVisibility(false))
                }
            }
        }

    }
}