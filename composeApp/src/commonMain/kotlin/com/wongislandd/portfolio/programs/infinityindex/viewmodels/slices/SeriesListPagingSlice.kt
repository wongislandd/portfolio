package com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices

import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.BaseListPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.PagedListUseCase
import com.wongislandd.portfolio.programs.infinityindex.models.local.Series
import com.wongislandd.portfolio.programs.infinityindex.models.network.NetworkSeries
import com.wongislandd.portfolio.programs.infinityindex.repositories.SeriesEntityRepository

abstract class SeriesListPagingSlice(
    repository: SeriesEntityRepository,
    useCase: PagedListUseCase
) : BaseListPagingSlice<NetworkSeries, Series>(
    repository, EntityType.SERIES, useCase
) {

    override fun getAdditionalPagingParams(): Map<String, Any> {
        return mapOf(
//            "seriesType" to "ongoing"
        )
    }
}