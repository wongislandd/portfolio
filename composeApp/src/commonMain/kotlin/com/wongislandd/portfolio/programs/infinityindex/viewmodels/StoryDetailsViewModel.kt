package com.wongislandd.portfolio.programs.infinityindex.viewmodels

import com.wongislandd.portfolio.programs.infinityindex.models.local.Story
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.EventBus
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.UiEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.BaseDetailsScreenStateSlice
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.BaseDetailsViewModel
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.RelatedCharactersPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.RelatedComicsPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.RelatedCreatorsPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.RelatedEventsPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.RelatedSeriesPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.SingleStorySlice

class StoryDetailsViewModel(
    screenStateSlice: BaseDetailsScreenStateSlice<Story>,
    comicResolutionSlice: RelatedComicsPagingSlice,
    singleStorySlice: SingleStorySlice,
    eventsResolutionSlice: RelatedEventsPagingSlice,
    creatorsResolutionSlice: RelatedCreatorsPagingSlice,
    charactersResolutionSlice: RelatedCharactersPagingSlice,
    seriesResolutionSlice: RelatedSeriesPagingSlice,
    uiEventBus: EventBus<UiEvent>,
    backChannelEventBus: EventBus<BackChannelEvent>
) : BaseDetailsViewModel<Story>(
    EntityType.STORIES,
    screenStateSlice,
    uiEventBus,
    backChannelEventBus,
    listOf(
        comicResolutionSlice,
        singleStorySlice,
        eventsResolutionSlice,
        creatorsResolutionSlice,
        charactersResolutionSlice,
        seriesResolutionSlice
    )
)