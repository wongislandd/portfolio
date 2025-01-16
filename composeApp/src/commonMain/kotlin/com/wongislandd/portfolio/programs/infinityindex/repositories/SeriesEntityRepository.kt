package com.wongislandd.portfolio.programs.infinityindex.repositories

import com.wongislandd.portfolio.programs.infinityindex.models.network.NetworkSeries
import com.wongislandd.portfolio.programs.infinityindex.models.local.Series
import com.wongislandd.portfolio.programs.infinityindex.transformers.SeriesTransformer
import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.NetworkDataWrapper
import com.wongislandd.portfolio.programs.infinityindex.infra.paging.BaseRepository
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import io.ktor.client.HttpClient
import io.ktor.util.reflect.typeInfo

class SeriesEntityRepository(
    seriesTransformer: SeriesTransformer,
    okHttpClient: HttpClient
) : BaseRepository<NetworkSeries, Series>(
    seriesTransformer,
    okHttpClient,
    EntityType.SERIES,
    typeInfo<NetworkDataWrapper<NetworkSeries>>()
)