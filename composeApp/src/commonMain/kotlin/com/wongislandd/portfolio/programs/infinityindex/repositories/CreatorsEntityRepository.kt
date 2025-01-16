package com.wongislandd.portfolio.programs.infinityindex.repositories

import com.wongislandd.portfolio.programs.infinityindex.models.local.Creator
import com.wongislandd.portfolio.programs.infinityindex.models.network.NetworkCreator
import com.wongislandd.portfolio.programs.infinityindex.transformers.CreatorTransformer
import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.NetworkDataWrapper
import com.wongislandd.portfolio.programs.infinityindex.infra.paging.BaseRepository
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import io.ktor.client.HttpClient
import io.ktor.util.reflect.typeInfo

class CreatorsEntityRepository(
    creatorTransformer: CreatorTransformer,
    okHttpClient: HttpClient
) : BaseRepository<NetworkCreator, Creator>(
    creatorTransformer,
    okHttpClient,
    EntityType.CREATORS,
    typeInfo<NetworkDataWrapper<NetworkCreator>>()
)