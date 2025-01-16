package com.wongislandd.portfolio.programs.infinityindex.viewmodels

import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.EventBus
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.UiEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.BaseListViewModel
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.SearchSlice
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.SortSlice
import com.wongislandd.portfolio.programs.infinityindex.models.local.Series
import com.wongislandd.portfolio.programs.infinityindex.models.network.NetworkSeries
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.RelatedSeriesPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.SeriesListScreenStateSlice

class RelatedSeriesListViewModel(
    uiEventBus: EventBus<UiEvent>,
    relatedSeriesPagingSlice: RelatedSeriesPagingSlice,
    searchSlice: SearchSlice,
    sortSlice: SortSlice,
    seriesListScreenStateSlice: SeriesListScreenStateSlice,
    backChannelEventBus: EventBus<BackChannelEvent>
) : BaseListViewModel<NetworkSeries, Series>(
    seriesListScreenStateSlice,
    sortSlice,
    searchSlice,
    relatedSeriesPagingSlice,
    uiEventBus,
    backChannelEventBus
)