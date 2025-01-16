package com.wongislandd.portfolio.programs.infinityindex.infra.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import com.wongislandd.portfolio.programs.infinityindex.ComicConstants
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityModel

@Composable
fun PagingWrapper(
    pagedEntities: LazyPagingItems<out EntityModel>,
    modifier: Modifier = Modifier,
    itemContent: @Composable (EntityModel) -> Unit,
    placeholderContent: @Composable () -> Unit,
    errorContent: @Composable (String) -> Unit,
    emptyContent: @Composable () -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(180.dp),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .background(MaterialTheme.colors.surface)
    ) {
        // Hide results if we are refreshing the whole source
        if (pagedEntities.loadState.refresh !is LoadState.Loading && pagedEntities.loadState.refresh !is LoadState.Error) {
            items(pagedEntities.itemCount) { index ->
                pagedEntities[index]?.let { entity ->
                    itemContent(entity)
                }
            }
        }
        pagedEntities.apply {
            if (loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading) {
                repeat(ComicConstants.LIST_PAGE_SIZE * 2) {
                    item {
                        placeholderContent()
                    }
                }
            }
            item(span = {
                GridItemSpan(maxLineSpan)
            }) {
                Box(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    when {
                        loadState.refresh is LoadState.Error || loadState.append is LoadState.Error -> {
                            val message =
                                (pagedEntities.loadState.append as? LoadState.Error)?.error?.message
                                    ?: (pagedEntities.loadState.refresh as? LoadState.Error)?.error?.message
                            errorContent(message ?: "Unknown error")
                        }

                        loadState.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached && itemCount == 0 -> {
                            emptyContent()
                        }
                    }
                }
            }
        }
    }
}