package com.wongislandd.portfolio.programs.infinityindex.browse

import app.cash.paging.PagingConfig
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.AllCharactersPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.AllComicsPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.AllCreatorsPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.AllEventsPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.AllSeriesPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.AllStoriesPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.infra.util.SliceableViewModel
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.EventBus
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.UiEvent

class BrowseViewModel(
    val screenStateSlice: BrowseScreenStateSlice,
    allComicsPagingSlice: AllComicsPagingSlice,
    allCreatorsPagingSlice: AllCreatorsPagingSlice,
    allCharactersPagingSlice: AllCharactersPagingSlice,
    allSeriesPagingSlice: AllSeriesPagingSlice,
    allStoriesPagingSlice: AllStoriesPagingSlice,
    allEventsPagingSlice: AllEventsPagingSlice,
    uiEventBus: EventBus<UiEvent>,
    backChannelEventBus: EventBus<BackChannelEvent>
) : SliceableViewModel(
    uiEventBus,
    backChannelEventBus
) {

    init {
        listOf(
            allComicsPagingSlice,
            allCreatorsPagingSlice,
            allCharactersPagingSlice,
            allSeriesPagingSlice,
            allStoriesPagingSlice,
            allEventsPagingSlice
        ).forEach {
            apply {
                it.setPagingConfig(
                    PagingConfig(
                        initialLoadSize = 8,
                        pageSize = 3,
                        enablePlaceholders = false,
                        prefetchDistance = 2
                    )
                )
            }
        }
        registerSlice(screenStateSlice)
        registerSlice(allComicsPagingSlice)
        registerSlice(allCreatorsPagingSlice)
        registerSlice(allCharactersPagingSlice)
        registerSlice(allSeriesPagingSlice)
        registerSlice(allStoriesPagingSlice)
        registerSlice(allEventsPagingSlice)
    }
}