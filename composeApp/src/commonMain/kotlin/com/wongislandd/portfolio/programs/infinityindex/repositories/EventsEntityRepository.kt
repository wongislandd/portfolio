package com.wongislandd.portfolio.programs.infinityindex.repositories

import com.wongislandd.portfolio.programs.infinityindex.models.local.Event
import com.wongislandd.portfolio.programs.infinityindex.models.network.NetworkEvent
import com.wongislandd.portfolio.programs.infinityindex.transformers.EventTransformer
import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.NetworkDataWrapper
import com.wongislandd.portfolio.programs.infinityindex.infra.paging.BaseRepository
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import io.ktor.client.HttpClient
import io.ktor.util.reflect.typeInfo

class EventsEntityRepository(
    eventTransformer: EventTransformer,
    okHttpClient: HttpClient,
) : BaseRepository<NetworkEvent, Event>(
    eventTransformer,
    okHttpClient,
    EntityType.EVENTS,
    typeInfo<NetworkDataWrapper<NetworkEvent>>()
)