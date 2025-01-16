package com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels

data class ComicListScreenState(
    val isDigitallyAvailableFilterEnabled: Boolean,
    val isVariantsEnabled: Boolean,
    override val listState: ListState
): BaseListScreenState(
    listState
)