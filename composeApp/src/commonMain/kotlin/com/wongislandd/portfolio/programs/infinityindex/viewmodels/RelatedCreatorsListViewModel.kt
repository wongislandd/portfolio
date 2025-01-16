package com.wongislandd.portfolio.programs.infinityindex.viewmodels

import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.EventBus
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.UiEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.BaseListViewModel
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.SearchSlice
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.SortSlice
import com.wongislandd.portfolio.programs.infinityindex.models.local.Creator
import com.wongislandd.portfolio.programs.infinityindex.models.network.NetworkCreator
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.CreatorsListScreenStateSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.RelatedCreatorsPagingSlice

class RelatedCreatorsListViewModel(
    uiEventBus: EventBus<UiEvent>,
    relatedCreatorsPagingSlice: RelatedCreatorsPagingSlice,
    searchSlice: SearchSlice,
    sortSlice: SortSlice,
    creatorsListScreenStateSlice: CreatorsListScreenStateSlice,
    backChannelEventBus: EventBus<BackChannelEvent>
) : BaseListViewModel<NetworkCreator, Creator>(
    creatorsListScreenStateSlice,
    sortSlice,
    searchSlice,
    relatedCreatorsPagingSlice,
    uiEventBus,
    backChannelEventBus
)