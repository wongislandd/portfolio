package com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices

import com.wongislandd.portfolio.programs.infinityindex.infra.DetailsBackChannelEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.BaseSupplementaryEntityResolutionSlice
import com.wongislandd.portfolio.programs.infinityindex.models.local.Series
import com.wongislandd.portfolio.programs.infinityindex.repositories.SeriesEntityRepository
import com.wongislandd.portfolio.programs.infinityindex.sortoptions.ComicsSortOption
import kotlinx.coroutines.launch

class ComicSeriesSupplementaryEntityResolutionSlice(
    seriesRepository: SeriesEntityRepository,
) : BaseSupplementaryEntityResolutionSlice<Series>(
    seriesRepository,
    EntityType.SERIES
) {
    override fun onSupplementaryEntityResolved(entity: Series) {
        // Send the beacon to find related comics if available
        sliceScope.launch {
            backChannelEvents.sendEvent(
                DetailsBackChannelEvent.RequestForPagination(
                    entity.id,
                    EntityType.SERIES,
                    EntityType.COMICS,
                    ComicsSortOption.ONSALE_DATE_DESC.sortKey,
                    "Comics in Series"
                )
            )
        }
    }
}