package com.wongislandd.portfolio.programs.infinityindex.viewmodels

import com.wongislandd.portfolio.programs.infinityindex.models.local.Creator
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.BaseDetailsViewModel
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.EventBus
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.UiEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.BaseDetailsScreenStateSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.RelatedCharactersPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.RelatedComicsPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.RelatedEventsPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.RelatedSeriesPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.RelatedStoriesPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.SingleCreatorSlice

class CreatorDetailsViewModel(
    screenStateSlice: BaseDetailsScreenStateSlice<Creator>,
    comicResolutionSlice: RelatedComicsPagingSlice,
    storiesResolutionSlice: RelatedStoriesPagingSlice,
    eventsResolutionSlice: RelatedEventsPagingSlice,
    singleCreatorSlice: SingleCreatorSlice,
    charactersResolutionSlice: RelatedCharactersPagingSlice,
    seriesResolutionSlice: RelatedSeriesPagingSlice,
    uiEventBus: EventBus<UiEvent>,
    backChannelEventBus: EventBus<BackChannelEvent>
) : BaseDetailsViewModel<Creator>(
    EntityType.CREATORS,
    screenStateSlice,
    uiEventBus,
    backChannelEventBus,
    listOf(
        comicResolutionSlice,
        storiesResolutionSlice,
        eventsResolutionSlice,
        singleCreatorSlice,
        charactersResolutionSlice,
        seriesResolutionSlice
    )
)