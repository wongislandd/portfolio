package com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels

import com.wongislandd.portfolio.programs.infinityindex.infra.DetailsBackChannelEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.paging.BaseRepository
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityModel
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import com.wongislandd.portfolio.programs.infinityindex.infra.util.Resource
import com.wongislandd.portfolio.programs.infinityindex.infra.util.Resource.Loading.onError
import com.wongislandd.portfolio.programs.infinityindex.infra.util.Resource.Loading.onSuccess
import com.wongislandd.portfolio.programs.infinityindex.infra.util.ViewModelSlice
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.BackChannelEvent
import kotlinx.coroutines.launch

abstract class BaseSupplementaryEntityResolutionSlice<LOCAL_TYPE : EntityModel>(
    private val repository: BaseRepository<*, LOCAL_TYPE>,
    private val entityType: EntityType
) : ViewModelSlice() {

    open fun onSupplementaryEntityResolved(entity: LOCAL_TYPE) {}

    override fun handleBackChannelEvent(event: BackChannelEvent) {
        when (event) {
            is DetailsBackChannelEvent.RequestForSingleRelatedDataUpdate -> {
                if (entityType == event.entityType) {
                    launchSingleGetResolution(event.entityId, event.titleOfResults)
                }
            }
        }
    }

    private fun launchSingleGetResolution(id: Int, titleOfResults: String?) {
        sliceScope.launch {
            backChannelEvents.sendEvent(
                DetailsBackChannelEvent.SupplementaryDataUpdate(
                    Resource.Loading
                )
            )
            val response = repository.get(id)
            response.onSuccess { data ->
                onSupplementaryEntityResolved(data)
                launch {
                    backChannelEvents.sendEvent(
                        DetailsBackChannelEvent.SupplementaryDataUpdate(
                            Resource.Success(
                                SupplementaryEntityData(
                                    titleOfResults,
                                    data
                                )
                            )
                        )
                    )
                }
            }
            response.onError { error, _ ->
                launch {
                    backChannelEvents.sendEvent(
                        DetailsBackChannelEvent.SupplementaryDataUpdate(
                            Resource.Error(error)
                        )
                    )
                }
            }
        }
    }
}