package com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels

import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityModel
import com.wongislandd.portfolio.programs.infinityindex.infra.util.SliceableViewModel
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.EventBus
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.UiEvent

abstract class BaseListViewModel<NETWORK_TYPE, T : EntityModel>(
    val screenStateSlice: BaseListScreenStateSlice<T>,
    sortSlice: SortSlice? = null,
    searchSlice: SearchSlice? = null,
    pagingSlice: BaseListPagingSlice<NETWORK_TYPE, T>,
    uiEventBus: EventBus<UiEvent>,
    backChannelEventBus: EventBus<BackChannelEvent>
) : SliceableViewModel(
    uiEventBus,
    backChannelEventBus
) {

    val isSortEnabled = sortSlice != null
    val isSearchEnabled = searchSlice != null

    init {
        registerSlice(screenStateSlice)
        sortSlice?.also {
            registerSlice(it)
        }
        searchSlice?.also {
            registerSlice(searchSlice)
        }
        registerSlice(pagingSlice)
    }
}