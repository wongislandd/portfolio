package com.wongislandd.portfolio.programs.infinityindex.viewmodels

import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.EventBus
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.UiEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.BaseListViewModel
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.ComicsListScreenStateSlice
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.SearchSlice
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.SortSlice
import com.wongislandd.portfolio.programs.infinityindex.models.local.Comic
import com.wongislandd.portfolio.programs.infinityindex.models.network.NetworkComic
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.RelatedComicsPagingSlice

class RelatedComicsListViewModel(
    uiEventBus: EventBus<UiEvent>,
    relatedComicsPagingSlice: RelatedComicsPagingSlice,
    searchSlice: SearchSlice,
    sortSlice: SortSlice,
    comicsListScreenStateSlice: ComicsListScreenStateSlice,
    backChannelEventBus: EventBus<BackChannelEvent>
) : BaseListViewModel<NetworkComic, Comic>(
    comicsListScreenStateSlice,
    sortSlice,
    searchSlice,
    relatedComicsPagingSlice,
    uiEventBus,
    backChannelEventBus
)