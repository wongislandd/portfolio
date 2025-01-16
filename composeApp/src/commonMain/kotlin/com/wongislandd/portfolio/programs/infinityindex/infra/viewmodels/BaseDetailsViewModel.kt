package com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels

import app.cash.paging.PagingConfig
import com.wongislandd.portfolio.programs.infinityindex.ComicConstants
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityModel
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import com.wongislandd.portfolio.programs.infinityindex.infra.util.SliceableViewModel
import com.wongislandd.portfolio.programs.infinityindex.infra.util.ViewModelSlice
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.EventBus
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.UiEvent

abstract class BaseDetailsViewModel<T : EntityModel>(
    val entityType: EntityType,
    val screenStateSlice: BaseDetailsScreenStateSlice<T>,
    uiEventBus: EventBus<UiEvent>,
    backChannelEventBus: EventBus<BackChannelEvent>,
    slices: List<ViewModelSlice>,
) : SliceableViewModel(
    uiEventBus,
    backChannelEventBus
) {

    init {
        registerSlice(screenStateSlice)
        slices.forEach {
            if (it is BaseListPagingSlice<*, *>) {
                it.setPagingConfig(
                    PagingConfig(
                        initialLoadSize = ComicConstants.RELATED_DETAILS_MAX_ENTITY_RESULTS,
                        pageSize = ComicConstants.RELATED_DETAILS_MAX_ENTITY_RESULTS,
                        enablePlaceholders = false
                    ),
                    1
                )
            }
            registerSlice(it)
        }
    }
}