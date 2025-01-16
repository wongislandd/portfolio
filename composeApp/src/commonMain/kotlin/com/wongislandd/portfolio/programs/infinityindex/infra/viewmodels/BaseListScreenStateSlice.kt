package com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels

import app.cash.paging.PagingData
import com.wongislandd.portfolio.programs.infinityindex.infra.PagingBackChannelEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityModel
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import com.wongislandd.portfolio.programs.infinityindex.infra.util.SelectableSortOption
import com.wongislandd.portfolio.programs.infinityindex.infra.util.ViewModelSlice
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.util.getSortOptions
import com.wongislandd.portfolio.programs.infinityindex.models.util.SearchState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

abstract class BaseListScreenStateSlice<T : EntityModel>(
    val entityType: EntityType
) : BaseScreenStateSlice<T>, ViewModelSlice() {

    private val _listPagingData: MutableStateFlow<PagingData<EntityModel>> =
        MutableStateFlow(PagingData.empty())

    private val _listState: MutableStateFlow<ListState> = MutableStateFlow(
        ListState(
            availableSortOptions = entityType.getSortOptions()
                .map { SelectableSortOption(it, it.isDefault) },
            searchState = SearchState(
                searchQuery = "",
                isSearchBoxVisible = false
            )
        )
    )

    val listPagingData: StateFlow<PagingData<EntityModel>> = _listPagingData

    val listState: StateFlow<ListState> = _listState

    override fun handleBackChannelEvent(event: BackChannelEvent) {
        when (event) {
            is PagingBackChannelEvent.PagingDataResUpdate -> {
                _listPagingData.value = event.update
            }

            is PagingBackChannelEvent.UpdateSearchBoxVisibility -> {
                _listState.update {
                    it.copy(
                        searchState = it.searchState.copy(
                            isSearchBoxVisible = event.isVisible
                        )
                    )
                }
            }

            is PagingBackChannelEvent.UpdatePendingSearchQuery -> {
                _listState.update {
                    it.copy(
                        searchState = it.searchState.copy(
                            searchQuery = event.query
                        )
                    )
                }
            }

            is PagingBackChannelEvent.SubmitSortSelection -> {
                _listState.update {
                    val newSortOptions = it.availableSortOptions.map { selectableSortOption ->
                        selectableSortOption.copy(
                            isSelected = selectableSortOption.sortOption == event.sortOption
                        )
                    }
                    it.copy(availableSortOptions = newSortOptions)
                }
            }
        }
    }
}