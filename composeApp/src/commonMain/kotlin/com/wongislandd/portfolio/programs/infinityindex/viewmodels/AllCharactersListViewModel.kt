package com.wongislandd.portfolio.programs.infinityindex.viewmodels

import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.EventBus
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.UiEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.BaseListViewModel
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.SearchSlice
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.SortSlice
import com.wongislandd.portfolio.programs.infinityindex.models.local.Character
import com.wongislandd.portfolio.programs.infinityindex.models.network.NetworkCharacter
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.AllCharactersPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.CharactersListScreenStateSlice

class AllCharactersListViewModel(
    screenStateSlice: CharactersListScreenStateSlice,
    sortSlice: SortSlice,
    searchSlice: SearchSlice,
    pagingSlice: AllCharactersPagingSlice,
    uiEventBus: EventBus<UiEvent>,
    backChannelEventBus: EventBus<BackChannelEvent>
) : BaseListViewModel<NetworkCharacter, Character>(
    screenStateSlice = screenStateSlice,
    sortSlice = sortSlice,
    searchSlice = searchSlice,
    pagingSlice = pagingSlice,
    uiEventBus = uiEventBus,
    backChannelEventBus = backChannelEventBus
)