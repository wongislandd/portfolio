package com.wongislandd.portfolio.programs.infinityindex.viewmodels

import com.wongislandd.portfolio.programs.infinityindex.models.local.Character
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.BaseDetailsViewModel
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.EventBus
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.UiEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.BaseDetailsScreenStateSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.RelatedComicsPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.RelatedCreatorsPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.RelatedEventsPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.RelatedSeriesPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.RelatedStoriesPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.SingleCharacterSlice

class CharacterDetailsViewModel(
    screenStateSlice: BaseDetailsScreenStateSlice<Character>,
    uiEventBus: EventBus<UiEvent>,
    backChannelEventBus: EventBus<BackChannelEvent>,
    singleCharacterSlice: SingleCharacterSlice,
    comicResolutionSlice: RelatedComicsPagingSlice,
    storiesResolutionSlice: RelatedStoriesPagingSlice,
    eventsResolutionSlice: RelatedEventsPagingSlice,
    creatorsResolutionSlice: RelatedCreatorsPagingSlice,
    seriesDetailsResolutionSlice: RelatedSeriesPagingSlice,
) : BaseDetailsViewModel<Character>(
    EntityType.CHARACTERS,
    screenStateSlice,
    uiEventBus,
    backChannelEventBus,
    listOf(
        singleCharacterSlice,
        comicResolutionSlice,
        storiesResolutionSlice,
        eventsResolutionSlice,
        creatorsResolutionSlice,
        seriesDetailsResolutionSlice
    )
)