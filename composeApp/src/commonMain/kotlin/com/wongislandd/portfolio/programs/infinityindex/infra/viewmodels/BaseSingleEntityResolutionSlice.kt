package com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels

import com.wongislandd.portfolio.programs.infinityindex.infra.DetailsBackChannelEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.DetailsUiEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.PagingBackChannelEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.paging.BaseRepository
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityModel
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import com.wongislandd.portfolio.programs.infinityindex.infra.util.Resource.Loading.onSuccess
import com.wongislandd.portfolio.programs.infinityindex.infra.util.ViewModelSlice
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.UiEvent
import kotlinx.coroutines.launch

/**
 * Good for getting details about a single entity, then broadcasting the
 * need for related entities to be loaded.
 */
abstract class BaseSingleEntityResolutionSlice<NETWORK_TYPE, LOCAL_TYPE : EntityModel>(
    private val entityType: EntityType,
    private val repository: BaseRepository<NETWORK_TYPE, LOCAL_TYPE>,
) : ViewModelSlice() {

    private var primaryResourceId: Int? = null

    override fun handleUiEvent(event: UiEvent) {
        when (event) {
            is DetailsUiEvent.PageInitialized -> {
                if (event.primaryEntityType == entityType && primaryResourceId != event.primaryId) {
                    this.primaryResourceId = event.primaryId
                    loadSingleEntity(event.primaryId)
                }
            }
        }
    }

    open fun onSingleEntityLoaded(entity: LOCAL_TYPE) {}

    private fun loadSingleEntity(primaryResourceId: Int) {
        sliceScope.launch {
            val singleEntityRes = repository.get(primaryResourceId)
            backChannelEvents.sendEvent(
                DetailsBackChannelEvent.SingleDataResUpdate(
                    update = singleEntityRes,
                    type = entityType
                )
            )
            // Send out the signals to start paging for related entities, see [BaseRelatedEntitiesSlice]
            singleEntityRes.onSuccess { entity ->
                onSingleEntityLoaded(entity)
                launch {
                    sendEntityCountSignals(entity)
                    sendRelatedPagingRequestSignals(primaryResourceId, entity)
                }
            }
        }
    }

    /**
     * Because we know the related entity counts from the single detail response,
     * we can update this early, instead of waiting for the pagination request metadata.
     */
    private suspend fun sendEntityCountSignals(entity: EntityModel) {
        mapOf(
            EntityType.CHARACTERS to entity.relatedCharactersCount,
            EntityType.COMICS to entity.relatedComicsCount,
            EntityType.CREATORS to entity.relatedCreatorsCount,
            EntityType.EVENTS to entity.relatedEventsCount,
            EntityType.SERIES to entity.relatedSeriesCount,
            EntityType.STORIES to entity.relatedStoriesCount
        ).forEach {
            if (it.value > 0) {
                backChannelEvents.sendEvent(
                    PagingBackChannelEvent.EntityCountsUpdate(
                        totalCount = it.value.toLong(),
                        entityType = it.key,
                    )
                )
            }
        }

    }

    private suspend fun sendRelatedPagingRequestSignals(
        primaryResourceId: Int,
        entity: LOCAL_TYPE
    ) {
        if (entity.relatedComicsCount > 0) {
            backChannelEvents.sendEvent(
                DetailsBackChannelEvent.RequestForPagination(
                    rootEntityId = primaryResourceId,
                    rootEntityType = entityType,
                    relatedEntityTypeToPageFor = EntityType.COMICS
                )
            )
        }
        if (entity.relatedCharactersCount > 0) {
            backChannelEvents.sendEvent(
                DetailsBackChannelEvent.RequestForPagination(
                    rootEntityId = primaryResourceId,
                    rootEntityType = entityType,
                    relatedEntityTypeToPageFor = EntityType.CHARACTERS
                )
            )
        }
        if (entity.relatedCreatorsCount > 0) {
            backChannelEvents.sendEvent(
                DetailsBackChannelEvent.RequestForPagination(
                    rootEntityId = primaryResourceId,
                    rootEntityType = entityType,
                    relatedEntityTypeToPageFor = EntityType.CREATORS
                )
            )
        }
        if (entity.relatedEventsCount > 0) {
            backChannelEvents.sendEvent(
                DetailsBackChannelEvent.RequestForPagination(
                    rootEntityId = primaryResourceId,
                    rootEntityType = entityType,
                    relatedEntityTypeToPageFor = EntityType.EVENTS
                )
            )
        }
        if (entity.relatedSeriesCount > 0) {
            backChannelEvents.sendEvent(
                DetailsBackChannelEvent.RequestForPagination(
                    rootEntityId = primaryResourceId,
                    rootEntityType = entityType,
                    relatedEntityTypeToPageFor = EntityType.SERIES
                )
            )
        }
        if (entity.relatedStoriesCount > 0) {
            backChannelEvents.sendEvent(
                DetailsBackChannelEvent.RequestForPagination(
                    rootEntityId = primaryResourceId,
                    rootEntityType = entityType,
                    relatedEntityTypeToPageFor = EntityType.STORIES
                )
            )
        }
    }
}