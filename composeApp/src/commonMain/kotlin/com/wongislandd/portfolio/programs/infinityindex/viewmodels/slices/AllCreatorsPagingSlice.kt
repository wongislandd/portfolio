package com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices

import com.wongislandd.portfolio.programs.infinityindex.models.local.Creator
import com.wongislandd.portfolio.programs.infinityindex.models.network.NetworkCreator
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.BaseListPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.PagedListUseCase
import com.wongislandd.portfolio.programs.infinityindex.repositories.CreatorsEntityRepository

class AllCreatorsPagingSlice(
    repository: CreatorsEntityRepository
): BaseListPagingSlice<NetworkCreator, Creator>(
    repository,
    EntityType.CREATORS,
    PagedListUseCase.ALL_AVAILABLE
)