package com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices

import com.wongislandd.portfolio.programs.infinityindex.infra.DetailsBackChannelEvent
import com.wongislandd.portfolio.programs.infinityindex.models.local.Comic
import com.wongislandd.portfolio.programs.infinityindex.models.network.NetworkComic
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.BaseSingleEntityResolutionSlice
import com.wongislandd.portfolio.programs.infinityindex.repositories.ComicsEntityRepository
import kotlinx.coroutines.launch

class SingleComicSlice(
    comicsRepository: ComicsEntityRepository
) : BaseSingleEntityResolutionSlice<NetworkComic, Comic>(
    EntityType.COMICS,
    comicsRepository
) {
    override fun onSingleEntityLoaded(entity: Comic) {
        // request for a series resolution if there is one
        entity.seriesEntityReference?.also {
            sliceScope.launch {
                backChannelEvents.sendEvent(
                    DetailsBackChannelEvent.RequestForSingleRelatedDataUpdate(
                        it.entityId,
                        EntityType.SERIES,
                    )
                )
            }
        }
    }
}