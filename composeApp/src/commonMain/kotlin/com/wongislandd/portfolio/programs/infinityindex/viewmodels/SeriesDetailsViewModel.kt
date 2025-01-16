package com.wongislandd.portfolio.programs.infinityindex.viewmodels

import com.wongislandd.portfolio.programs.infinityindex.models.local.Series
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
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.RelatedStoriesPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.SingleSeriesSlice

class SeriesDetailsViewModel(
    screenStateSlice: BaseDetailsScreenStateSlice<Series>,
    comicResolutionSlice: RelatedComicsPagingSlice,
    storiesResolutionSlice: RelatedStoriesPagingSlice,
    eventsResolutionSlice: RelatedEventsPagingSlice,
    creatorsResolutionSlice: RelatedCreatorsPagingSlice,
    charactersResolutionSlice: RelatedCharactersPagingSlice,
    singleSeriesSlice: SingleSeriesSlice,
    uiEventBus: EventBus<UiEvent>,
    backChannelEventBus: EventBus<BackChannelEvent>
) : BaseDetailsViewModel<Series>(
    EntityType.SERIES,
    screenStateSlice,

    uiEventBus,
    backChannelEventBus,
    listOf(
        comicResolutionSlice,
        storiesResolutionSlice,
        eventsResolutionSlice,
        creatorsResolutionSlice,
        charactersResolutionSlice,
        singleSeriesSlice
    )
)