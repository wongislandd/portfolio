package com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices

import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.PagedListUseCase
import com.wongislandd.portfolio.programs.infinityindex.repositories.CachingPreferenceRepository
import com.wongislandd.portfolio.programs.infinityindex.repositories.ComicsEntityRepository

class RelatedComicsPagingSlice(
    comicsRepository: ComicsEntityRepository,
    cachingPreferenceRepository: CachingPreferenceRepository
) : ComicsListPagingSlice(
    comicsRepository,
    PagedListUseCase.RELATED_ENTITIES,
    cachingPreferenceRepository
)