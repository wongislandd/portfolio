package com.wongislandd.portfolio.programs.infinityindex.infra

import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.UiEvent

sealed class DetailsUiEvent: UiEvent {
    data class PageInitialized(val primaryId: Int, val primaryEntityType: EntityType): DetailsUiEvent()
    data class RelatedListInitialized(val primaryId: Int, val primaryEntityType: EntityType): DetailsUiEvent()
}