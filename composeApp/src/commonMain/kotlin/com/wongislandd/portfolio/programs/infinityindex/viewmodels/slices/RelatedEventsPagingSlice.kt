package com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices

import com.wongislandd.portfolio.programs.infinityindex.models.local.Event
import com.wongislandd.portfolio.programs.infinityindex.models.network.NetworkEvent
import com.wongislandd.portfolio.programs.infinityindex.repositories.EventsEntityRepository
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.BaseListPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.PagedListUseCase

class RelatedEventsPagingSlice(
    eventsRepository: EventsEntityRepository
) : BaseListPagingSlice<NetworkEvent, Event>(
    eventsRepository,
    EntityType.EVENTS,
    PagedListUseCase.RELATED_ENTITIES
)