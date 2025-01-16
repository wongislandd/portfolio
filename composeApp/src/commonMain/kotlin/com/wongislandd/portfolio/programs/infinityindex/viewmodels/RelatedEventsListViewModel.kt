package com.wongislandd.portfolio.programs.infinityindex.viewmodels

import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.EventBus
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.UiEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.BaseListViewModel
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.SearchSlice
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.SortSlice
import com.wongislandd.portfolio.programs.infinityindex.models.local.Event
import com.wongislandd.portfolio.programs.infinityindex.models.network.NetworkEvent
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.EventsListScreenStateSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.RelatedEventsPagingSlice

class RelatedEventsListViewModel(
    uiEventBus: EventBus<UiEvent>,
    relatedEventsPagingSlice: RelatedEventsPagingSlice,
    searchSlice: SearchSlice,
    sortSlice: SortSlice,
    eventsListScreenStateSlice: EventsListScreenStateSlice,
    backChannelEventBus: EventBus<BackChannelEvent>
) : BaseListViewModel<NetworkEvent, Event>(
    eventsListScreenStateSlice,
    sortSlice,
    searchSlice,
    relatedEventsPagingSlice,
    uiEventBus,
    backChannelEventBus
)