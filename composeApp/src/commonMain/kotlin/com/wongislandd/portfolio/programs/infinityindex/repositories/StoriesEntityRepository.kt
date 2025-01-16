package com.wongislandd.portfolio.programs.infinityindex.repositories

import com.wongislandd.portfolio.programs.infinityindex.models.network.NetworkStory
import com.wongislandd.portfolio.programs.infinityindex.models.local.Story
import com.wongislandd.portfolio.programs.infinityindex.transformers.StoryTransformer
import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.NetworkDataWrapper
import com.wongislandd.portfolio.programs.infinityindex.infra.paging.BaseRepository
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import io.ktor.client.HttpClient
import io.ktor.util.reflect.typeInfo

class StoriesEntityRepository(
    storyTransformer: StoryTransformer,
    okHttpClient: HttpClient
) : BaseRepository<NetworkStory, Story>(
    storyTransformer,
    okHttpClient,
    EntityType.STORIES,
    typeInfo<NetworkDataWrapper<NetworkStory>>()
)