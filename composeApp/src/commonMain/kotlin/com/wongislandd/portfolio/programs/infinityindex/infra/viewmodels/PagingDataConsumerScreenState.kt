package com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels

import androidx.paging.PagingData
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityModel
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import kotlinx.coroutines.flow.StateFlow

data class EntityPagingData(
    val pagingData: StateFlow<PagingData<EntityModel>>,
    val pagingTitle: String? = null,
    val entityCount: Int? = null,
    val entityType: EntityType
)

interface PagingDataConsumerScreenState {
    val characterData: StateFlow<EntityPagingData>
    val creatorsData: StateFlow<EntityPagingData>
    val eventsData: StateFlow<EntityPagingData>
    val storiesData: StateFlow<EntityPagingData>
    val seriesData: StateFlow<EntityPagingData>
    val comicData: StateFlow<EntityPagingData>
}