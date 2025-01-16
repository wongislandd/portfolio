package com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels

import androidx.paging.PagingData
import com.wongislandd.portfolio.programs.infinityindex.infra.PagingBackChannelEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityModel
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import com.wongislandd.portfolio.programs.infinityindex.infra.util.ViewModelSlice
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.BackChannelEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

/**
 * Pairs well with [PagingDataConsumerScreenState]
 */
abstract class BaseAllEntitiesPagingDataConsumerSlice : ViewModelSlice() {

    private val mutablePagingDatas: Map<EntityType, MutableStateFlow<PagingData<EntityModel>>> =
        mapOf(
            EntityType.CHARACTERS to MutableStateFlow(PagingData.empty()),
            EntityType.CREATORS to MutableStateFlow(PagingData.empty()),
            EntityType.EVENTS to MutableStateFlow(PagingData.empty()),
            EntityType.STORIES to MutableStateFlow(PagingData.empty()),
            EntityType.SERIES to MutableStateFlow(PagingData.empty()),
            EntityType.COMICS to MutableStateFlow(PagingData.empty())
        )

    protected val characterWrappedPagingData: MutableStateFlow<EntityPagingData> =
        MutableStateFlow(
            EntityPagingData(
                pagingData = getPagingData(EntityType.CHARACTERS),
                pagingTitle = "Characters",
                entityType = EntityType.CHARACTERS
            )
        )

    protected val creatorsWrappedPagingData: MutableStateFlow<EntityPagingData> =
        MutableStateFlow(
            EntityPagingData(
                pagingData = getPagingData(EntityType.CREATORS),
                pagingTitle = "Creators",
                entityType = EntityType.CREATORS
            )
        )

    protected val eventsWrappedPagingData: MutableStateFlow<EntityPagingData> =
        MutableStateFlow(
            EntityPagingData(
                pagingData = getPagingData(EntityType.EVENTS),
                pagingTitle = "Events",
                entityType = EntityType.EVENTS
            )
        )

    protected val storiesWrappedPagingData: MutableStateFlow<EntityPagingData> =
        MutableStateFlow(
            EntityPagingData(
                pagingData = getPagingData(EntityType.STORIES),
                pagingTitle = "Stories",
                entityType = EntityType.STORIES
            )
        )

    protected val seriesWrappedPagingData: MutableStateFlow<EntityPagingData> =
        MutableStateFlow(
            EntityPagingData(
                pagingData = getPagingData(EntityType.SERIES),
                pagingTitle = "Series",
                entityType = EntityType.SERIES
            )
        )

    protected val comicWrappedPagingData: MutableStateFlow<EntityPagingData> =
        MutableStateFlow(
            EntityPagingData(
                pagingData = getPagingData(EntityType.COMICS),
                pagingTitle = "Comics",
                entityType = EntityType.COMICS
            )
        )

    private val wrappedPagingDataMap = mapOf(
        EntityType.CHARACTERS to characterWrappedPagingData,
        EntityType.CREATORS to creatorsWrappedPagingData,
        EntityType.EVENTS to eventsWrappedPagingData,
        EntityType.STORIES to storiesWrappedPagingData,
        EntityType.SERIES to seriesWrappedPagingData,
        EntityType.COMICS to comicWrappedPagingData
    )


    private fun getPagingData(entityType: EntityType): MutableStateFlow<PagingData<EntityModel>> {
        return mutablePagingDatas[entityType]
            ?: throw IllegalStateException("No paging data found for $entityType")
    }

    private fun getWrappedPagingData(entityType: EntityType): MutableStateFlow<EntityPagingData> {
        return wrappedPagingDataMap[entityType]
            ?: throw IllegalStateException("No wrapped paging data found for $entityType")
    }


    override fun handleBackChannelEvent(event: BackChannelEvent) {
        when (event) {
            is PagingBackChannelEvent.PagingDataResUpdate -> {
                handlePagingUpdate(event)
            }

            is PagingBackChannelEvent.EntityCountsUpdate -> {
                handleEntityCountsUpdate(event)
            }
        }
    }

    /**
     * Find a better way around the genericness here, so that you don't need an unchecked cast.
     * Although this is kind of safe, as long as the right entity type is passed!
     */
    private fun handlePagingUpdate(event: PagingBackChannelEvent.PagingDataResUpdate) {
        getPagingData(event.entityType).update {
            event.update
        }
        getWrappedPagingData(event.entityType).update {
            it.copy(pagingTitle = event.titleOfResults)
        }
    }

    private fun handleEntityCountsUpdate(event: PagingBackChannelEvent.EntityCountsUpdate) {
        getWrappedPagingData(event.entityType).update {
            it.copy(entityCount = event.totalCount.toInt())
        }
    }
}