package com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices

import com.wongislandd.portfolio.programs.infinityindex.models.network.NetworkStory
import com.wongislandd.portfolio.programs.infinityindex.models.local.Story
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.BaseSingleEntityResolutionSlice
import com.wongislandd.portfolio.programs.infinityindex.repositories.StoriesEntityRepository

class SingleStorySlice(
    storyRepository: StoriesEntityRepository
): BaseSingleEntityResolutionSlice<NetworkStory, Story>(
    EntityType.STORIES,
    storyRepository
)