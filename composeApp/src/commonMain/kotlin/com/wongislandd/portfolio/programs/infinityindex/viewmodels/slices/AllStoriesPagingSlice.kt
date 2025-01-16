package com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices

import com.wongislandd.portfolio.programs.infinityindex.models.network.NetworkStory
import com.wongislandd.portfolio.programs.infinityindex.models.local.Story
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.BaseListPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.PagedListUseCase
import com.wongislandd.portfolio.programs.infinityindex.repositories.StoriesEntityRepository

class AllStoriesPagingSlice(
    repository: StoriesEntityRepository
) : BaseListPagingSlice<NetworkStory, Story>(
    repository,
    EntityType.STORIES,
    PagedListUseCase.ALL_AVAILABLE
)