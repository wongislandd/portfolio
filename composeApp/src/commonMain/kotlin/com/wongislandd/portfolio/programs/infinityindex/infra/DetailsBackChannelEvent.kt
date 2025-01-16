package com.wongislandd.portfolio.programs.infinityindex.infra

import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityModel
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import com.wongislandd.portfolio.programs.infinityindex.infra.util.Resource
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.SupplementaryEntityData

sealed class DetailsBackChannelEvent : BackChannelEvent {

    data class SingleDataResUpdate<T : EntityModel>(val update: Resource<T>, val type: EntityType) :
        BackChannelEvent

    data class RequestForPagination(
        val rootEntityId: Int,
        val rootEntityType: EntityType,
        val relatedEntityTypeToPageFor: EntityType,
        val sortKey: String? = null,
        val titleOfResults: String? = null
    ) : BackChannelEvent

    data class RequestForSingleRelatedDataUpdate(
        val entityId: Int, val entityType: EntityType,
        val titleOfResults: String? = null
    ) :
        BackChannelEvent

    data class SupplementaryDataUpdate(
        val supplementaryDataRes: Resource<SupplementaryEntityData>
    ) : BackChannelEvent

}