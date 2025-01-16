package com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices

import com.wongislandd.portfolio.programs.infinityindex.models.local.Creator
import com.wongislandd.portfolio.programs.infinityindex.models.network.NetworkCreator
import com.wongislandd.portfolio.programs.infinityindex.repositories.CreatorsEntityRepository
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.BaseListPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.PagedListUseCase

class RelatedCreatorsPagingSlice(
    creatorsRepository: CreatorsEntityRepository
) : BaseListPagingSlice<NetworkCreator, Creator>(
    creatorsRepository,
    EntityType.CREATORS,
    PagedListUseCase.RELATED_ENTITIES
)