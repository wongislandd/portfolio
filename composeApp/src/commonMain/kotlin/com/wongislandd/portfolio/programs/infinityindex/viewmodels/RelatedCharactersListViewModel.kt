package com.wongislandd.portfolio.programs.infinityindex.viewmodels

import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.EventBus
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.UiEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.BaseListViewModel
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.SearchSlice
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.SortSlice
import com.wongislandd.portfolio.programs.infinityindex.models.local.Character
import com.wongislandd.portfolio.programs.infinityindex.models.network.NetworkCharacter
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.CharactersListScreenStateSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.RelatedCharactersPagingSlice

class RelatedCharactersListViewModel(
    uiEventBus: EventBus<UiEvent>,
    searchSlice: SearchSlice,
    sortSlice: SortSlice,
    relatedCharactersPagingSlice: RelatedCharactersPagingSlice,
    charactersListScreenStateSlice: CharactersListScreenStateSlice,
    backChannelEventBus: EventBus<BackChannelEvent>
) : BaseListViewModel<NetworkCharacter, Character>(
    charactersListScreenStateSlice,
    sortSlice,
    searchSlice,
    relatedCharactersPagingSlice,
    uiEventBus,
    backChannelEventBus
)