package com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels

import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityModel
import com.wongislandd.portfolio.programs.infinityindex.infra.util.Resource
import kotlinx.coroutines.flow.StateFlow

// nothing will flow through the non-primary res data, wondering if we can
// make the types of other paging data variable
data class BaseDetailsScreenState<T : EntityModel>(
    val primaryId: Int? = null,
    val primaryRes: Resource<T> = Resource.Loading,
    val supplementaryEntityData: StateFlow<Resource<SupplementaryEntityData>>,
    override val characterData: StateFlow<EntityPagingData>,
    override val creatorsData: StateFlow<EntityPagingData>,
    override val eventsData: StateFlow<EntityPagingData>,
    override val storiesData: StateFlow<EntityPagingData>,
    override val seriesData: StateFlow<EntityPagingData>,
    override val comicData: StateFlow<EntityPagingData>,
) : PagingDataConsumerScreenState

data class SupplementaryEntityData(
    val title: String? = null,
    val entity: EntityModel
)