package com.wongislandd.portfolio.programs.infinityindex.infra.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import com.wongislandd.portfolio.programs.infinityindex.ComicConstants
import com.wongislandd.portfolio.programs.infinityindex.ComicConstants.RELATED_DETAILS_MAX_ENTITY_RESULTS
import com.wongislandd.portfolio.programs.infinityindex.infra.navigation.LocalNavHostController
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityModel
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType

@Composable
fun <T : EntityModel> SectionedEntityList(
    entityType: EntityType,
    totalItemCount: Int?,
    pagedItems: LazyPagingItems<T>,
    showAllRoute: String,
    title: String? = null,
    useCase: EntitiesListUseCase,
    modifier: Modifier = Modifier
) {
    val navController = LocalNavHostController.current
    val isShowAllAvailable =
        (totalItemCount ?: 0) > RELATED_DETAILS_MAX_ENTITY_RESULTS
    if (!pagedItems.loadState.isInitializing() && pagedItems.itemCount == 0) {
        return
    }
    Column(modifier = modifier.fillMaxWidth()) {
        EntitySectionHeader(
            entityType = entityType,
            totalEntityCount = totalItemCount,
            title = title,
            isContentInitializing = pagedItems.loadState.isInitializing(),
            showAllRoute = showAllRoute.takeIf { isShowAllAvailable && useCase == EntitiesListUseCase.BROWSE },
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                horizontal = 16.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(
                count = pagedItems.itemCount
            ) { index ->
                pagedItems[index]?.let { entity ->
                    EntityCard(entity)
                }
            }
            pagedItems.apply {
                when {
                    // Placeholder cards
                    loadState.isInitializing() -> {
                        val backupNumGhostCards =
                            if (useCase == EntitiesListUseCase.DETAILS) RELATED_DETAILS_MAX_ENTITY_RESULTS else ComicConstants.LIST_PAGE_SIZE
                        val numGhostCards = totalItemCount ?: backupNumGhostCards
                        repeat(
                            numGhostCards
                        ) {
                            item {
                                GhostEntityCard()
                            }
                        }
                    }

                    loadState.isDoneLoading() -> {
                        // If there are more results to show
                        if (isShowAllAvailable)
                            item {
                                SeeMoreEntityCard(modifier = Modifier
                                    .clickable {
                                        navController.navigate(
                                            showAllRoute
                                        )
                                    })
                            }
                    }

                    loadState.append == LoadState.Loading -> {
                        item {
                            CircularProgressIndicator()
                        }
                    }

                    loadState.refresh is LoadState.Error || loadState.append is LoadState.Error -> {
                        val message =
                            (pagedItems.loadState.append as? LoadState.Error)?.error?.message
                                ?: (pagedItems.loadState.refresh as? LoadState.Error)?.error?.message
                        item {
                            GenericErrorScreen(message ?: "Unknown error")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EntitySectionHeader(
    entityType: EntityType,
    totalEntityCount: Int?,
    title: String? = null,
    isContentInitializing: Boolean = false,
    showAllRoute: String?,
    modifier: Modifier = Modifier
) {
    val navController = LocalNavHostController.current
    val headerTitle = title ?: entityType.displayName
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isContentInitializing) {
            ShimmerEffect {
                Card(
                    modifier = Modifier.size(height = 36.dp, width = 200.dp).padding(start = 8.dp),
                    shape = RoundedCornerShape(8.dp),
                    backgroundColor = MaterialTheme.colors.primary
                ) {}
            }
        } else {
            val sectionHeaderText =
                totalEntityCount?.let { "$headerTitle (${formatNumberWithCommas(it)})" }
                    ?: headerTitle
            Text(
                text = sectionHeaderText,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start,
                modifier = modifier.padding(vertical = 8.dp)
            )
            showAllRoute?.also {
                Row(modifier = Modifier.clickable {
                    navController.navigate(
                        showAllRoute
                    )
                }) {
                    Text(text = "See all", fontWeight = FontWeight.Thin)
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "See all"
                    )
                }
            }
        }
    }

}

private fun CombinedLoadStates.isInitializing() = this.refresh == LoadState.Loading

private fun CombinedLoadStates.isDoneLoading() =
    this.refresh is LoadState.NotLoading && this.append.endOfPaginationReached

private fun formatNumberWithCommas(number: Int): String {
    return number.toString()
        .reversed()
        .chunked(3)
        .joinToString(",")
        .reversed()
}