package com.wongislandd.portfolio.programs.infinityindex.infra

import com.wongislandd.portfolio.programs.infinityindex.infra.util.SortOption
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.UiEvent

sealed class ListUiEvent : UiEvent {

    data object SearchClicked: ListUiEvent()

    data class SortSelected(val sortOption: SortOption): ListUiEvent()

    data class SubmitSearchQuery(val query: String): ListUiEvent()

    data class SetPendingSearchQuery(val query: String): ListUiEvent()

    data class ToggleDigitalAvailabilityFilter(val selected: Boolean): ListUiEvent()

    data class ToggleVariantsFilter(val selected: Boolean): ListUiEvent()

    data object ClearSearchQuery: ListUiEvent()
}