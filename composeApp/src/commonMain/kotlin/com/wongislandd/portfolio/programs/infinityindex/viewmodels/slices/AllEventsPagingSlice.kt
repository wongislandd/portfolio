package com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices

import com.wongislandd.portfolio.programs.infinityindex.models.local.Event
import com.wongislandd.portfolio.programs.infinityindex.models.network.NetworkEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.BaseListPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.PagedListUseCase
import com.wongislandd.portfolio.programs.infinityindex.repositories.EventsEntityRepository

class AllEventsPagingSlice(repository: EventsEntityRepository) :
    BaseListPagingSlice<NetworkEvent, Event>(
        repository,
        EntityType.EVENTS,
        PagedListUseCase.ALL_AVAILABLE
)