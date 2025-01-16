package com.wongislandd.portfolio.programs.infinityindex.infra.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.collectAsLazyPagingItems
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.EntityPagingData
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.PagingDataConsumerScreenState

enum class EntitiesListUseCase {
    BROWSE,
    DETAILS
}

@Composable
fun ListOfEntities(
    screenState: PagingDataConsumerScreenState,
    showAllRouteGetter: (EntityType) -> String,
    useCase: EntitiesListUseCase,
    modifier: Modifier = Modifier,
) {
    val charactersData = screenState.characterData.collectAsState()
    val creatorsData = screenState.creatorsData.collectAsState()
    val eventsData = screenState.eventsData.collectAsState()
    val storiesData = screenState.storiesData.collectAsState()
    val seriesData = screenState.seriesData.collectAsState()
    val comicData = screenState.comicData.collectAsState()

    // Controls the order of the sections
    val sections = listOf(
        comicData,
        seriesData,
        eventsData,
        charactersData,
        creatorsData,
        storiesData,
    )

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        sections.forEach { entitySection ->
            PagedEntity(entitySection.value, showAllRouteGetter(entitySection.value.entityType), useCase)
        }
    }
}

@Composable
private fun PagedEntity(
    entityPagingData: EntityPagingData,
    showAllRoute: String,
    useCase: EntitiesListUseCase,
    modifier: Modifier = Modifier
) {
    val pagingItems = entityPagingData.pagingData.collectAsLazyPagingItems()
    val pagingTitle = entityPagingData.pagingTitle
    val entityCount = entityPagingData.entityCount
    val shouldShowList = entityCount != null || useCase == EntitiesListUseCase.BROWSE
    if (shouldShowList) {
        SectionedEntityList(
            entityType = entityPagingData.entityType,
            totalItemCount = entityCount,
            pagedItems = pagingItems,
            title = pagingTitle,
            showAllRoute = showAllRoute,
            useCase = EntitiesListUseCase.BROWSE,
            modifier = modifier
        )
    }
}