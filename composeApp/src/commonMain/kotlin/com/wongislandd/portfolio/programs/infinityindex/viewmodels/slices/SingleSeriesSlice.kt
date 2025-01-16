package com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices

import com.wongislandd.portfolio.programs.infinityindex.models.network.NetworkSeries
import com.wongislandd.portfolio.programs.infinityindex.models.local.Series
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.BaseSingleEntityResolutionSlice
import com.wongislandd.portfolio.programs.infinityindex.repositories.SeriesEntityRepository

class SingleSeriesSlice(
    seriesRepository: SeriesEntityRepository
): BaseSingleEntityResolutionSlice<NetworkSeries, Series>(
    EntityType.SERIES,
    seriesRepository
)