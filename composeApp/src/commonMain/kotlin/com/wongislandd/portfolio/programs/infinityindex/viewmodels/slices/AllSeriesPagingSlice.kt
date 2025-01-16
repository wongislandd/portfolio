package com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices

import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.PagedListUseCase
import com.wongislandd.portfolio.programs.infinityindex.repositories.SeriesEntityRepository

class AllSeriesPagingSlice(
    repository: SeriesEntityRepository
) : SeriesListPagingSlice(
    repository,
    PagedListUseCase.ALL_AVAILABLE
)