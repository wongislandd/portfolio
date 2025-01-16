package com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices

import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.PagedListUseCase
import com.wongislandd.portfolio.programs.infinityindex.repositories.CachingPreferenceRepository
import com.wongislandd.portfolio.programs.infinityindex.repositories.ComicsEntityRepository

class AllComicsPagingSlice(
    repository: ComicsEntityRepository,
    cachingPreferenceRepository: CachingPreferenceRepository
): ComicsListPagingSlice(
    repository,
    PagedListUseCase.ALL_AVAILABLE,
    cachingPreferenceRepository
)