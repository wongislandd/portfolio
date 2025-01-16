package com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels

import com.wongislandd.portfolio.programs.infinityindex.infra.DetailsBackChannelEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityModel
import com.wongislandd.portfolio.programs.infinityindex.infra.util.Resource
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.BackChannelEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

abstract class BaseDetailsScreenStateSlice<T : EntityModel> : BaseScreenStateSlice<T>,
    BaseAllEntitiesPagingDataConsumerSlice() {

    private val _supplementaryEntityData: MutableStateFlow<Resource<SupplementaryEntityData>> =
        MutableStateFlow(Resource.NotLoading)

    private val _screenState: MutableStateFlow<BaseDetailsScreenState<T>> =
        MutableStateFlow(
            BaseDetailsScreenState(
                supplementaryEntityData = _supplementaryEntityData,
                characterData = characterWrappedPagingData,
                creatorsData = creatorsWrappedPagingData,
                eventsData = eventsWrappedPagingData,
                storiesData = storiesWrappedPagingData,
                seriesData = seriesWrappedPagingData,
                comicData = comicWrappedPagingData,
            )
        )

    val screenState: StateFlow<BaseDetailsScreenState<T>> = _screenState

    @Suppress("UNCHECKED_CAST")
    override fun handleBackChannelEvent(event: BackChannelEvent) {
        super.handleBackChannelEvent(event)
        when (event) {
            is DetailsBackChannelEvent.SingleDataResUpdate<*> -> {
                (event.update as? Resource<T>)?.let { updateData ->
                    _screenState.update {
                        it.copy(primaryRes = updateData)
                    }
                }
            }

            is DetailsBackChannelEvent.SupplementaryDataUpdate -> {
                _supplementaryEntityData.update {
                    event.supplementaryDataRes
                }
            }
        }
    }
}