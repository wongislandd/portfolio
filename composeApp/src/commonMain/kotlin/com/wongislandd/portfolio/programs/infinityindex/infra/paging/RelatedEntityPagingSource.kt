package com.wongislandd.portfolio.programs.infinityindex.infra.paging

import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.DataWrapper
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityModel
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import com.wongislandd.portfolio.programs.infinityindex.infra.util.Resource

class RelatedEntityPagingSource<NETWORK_MODEL, LOCAL_MODEL: EntityModel>(
    private val primaryEntityRepository: BaseRepository<NETWORK_MODEL, LOCAL_MODEL>,
    private val relatedEntityType: EntityType,
    private val relatedEntityId: Int,
    private val searchQuery: String? = null,
    private val sortKey: String? = null,
    private val additionalPagingParams: Map<String, Any> = emptyMap()
) : BasePagingSource<LOCAL_MODEL>() {
    override suspend fun fetchData(start: Int, count: Int): Resource<DataWrapper<LOCAL_MODEL>> {
        return primaryEntityRepository.getPagedPrimaryEntityRelatedToOtherEntity(
            relatedEntityType,
            relatedEntityId,
            searchQuery,
            sortKey,
            start,
            count,
            additionalPagingParams
        )
    }
}