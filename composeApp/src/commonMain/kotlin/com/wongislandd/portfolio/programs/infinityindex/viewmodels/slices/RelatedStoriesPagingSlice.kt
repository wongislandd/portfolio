package com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices

import com.wongislandd.portfolio.programs.infinityindex.repositories.StoriesEntityRepository
import com.wongislandd.portfolio.programs.infinityindex.models.network.NetworkStory
import com.wongislandd.portfolio.programs.infinityindex.models.local.Story
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.BaseListPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.PagedListUseCase

class RelatedStoriesPagingSlice(
    storiesRepository: StoriesEntityRepository
) : BaseListPagingSlice<NetworkStory, Story>(
    storiesRepository,
    EntityType.STORIES,
    PagedListUseCase.RELATED_ENTITIES
)