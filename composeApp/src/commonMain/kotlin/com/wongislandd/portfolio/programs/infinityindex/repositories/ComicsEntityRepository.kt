package com.wongislandd.portfolio.programs.infinityindex.repositories

import com.wongislandd.portfolio.programs.infinityindex.models.local.Comic
import com.wongislandd.portfolio.programs.infinityindex.transformers.ComicTransformer
import com.wongislandd.portfolio.programs.infinityindex.models.network.NetworkComic
import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.NetworkDataWrapper
import com.wongislandd.portfolio.programs.infinityindex.infra.paging.BaseRepository
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import io.ktor.client.HttpClient
import io.ktor.util.reflect.typeInfo

class ComicsEntityRepository(
    detailComicDataWrapperTransformer: ComicTransformer,
    okHttpClient: HttpClient
) : BaseRepository<NetworkComic, Comic>(
    detailComicDataWrapperTransformer,
    okHttpClient,
    EntityType.COMICS,
    typeInfo<NetworkDataWrapper<NetworkComic>>()
)