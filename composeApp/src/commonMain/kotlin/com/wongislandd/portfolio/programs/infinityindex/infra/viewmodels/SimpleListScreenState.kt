package com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels

import com.wongislandd.portfolio.programs.infinityindex.models.util.SearchState
import com.wongislandd.portfolio.programs.infinityindex.infra.util.SelectableSortOption

data class ListState(
    val availableSortOptions: List<SelectableSortOption>,
    val searchState: SearchState
)

open class BaseListScreenState(
    open val listState: ListState
)