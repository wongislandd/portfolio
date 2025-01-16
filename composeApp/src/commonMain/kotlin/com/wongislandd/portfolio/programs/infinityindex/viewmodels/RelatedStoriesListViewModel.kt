package com.wongislandd.portfolio.programs.infinityindex.viewmodels

import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.StoriesListScreenStateSlice
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.EventBus
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.UiEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.BaseListViewModel
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.SearchSlice
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.SortSlice
import com.wongislandd.portfolio.programs.infinityindex.models.local.Story
import com.wongislandd.portfolio.programs.infinityindex.models.network.NetworkStory
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.RelatedStoriesPagingSlice

class RelatedStoriesListViewModel(
    uiEventBus: EventBus<UiEvent>,
    relatedStoriesPagingSlice: RelatedStoriesPagingSlice,
    searchSlice: SearchSlice,
    sortSlice: SortSlice,
    storiesListScreenStateSlice: StoriesListScreenStateSlice,
    backChannelEventBus: EventBus<BackChannelEvent>
) : BaseListViewModel<NetworkStory, Story>(
    storiesListScreenStateSlice,
    sortSlice,
    searchSlice,
    relatedStoriesPagingSlice,
    uiEventBus,
    backChannelEventBus
)