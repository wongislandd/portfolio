package com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices

import com.wongislandd.portfolio.programs.infinityindex.models.local.Event
import com.wongislandd.portfolio.programs.infinityindex.models.network.NetworkEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.BaseSingleEntityResolutionSlice
import com.wongislandd.portfolio.programs.infinityindex.repositories.EventsEntityRepository

class SingleEventSlice(
    eventsRepository: EventsEntityRepository
): BaseSingleEntityResolutionSlice<NetworkEvent, Event>(
    EntityType.EVENTS,
    eventsRepository
)