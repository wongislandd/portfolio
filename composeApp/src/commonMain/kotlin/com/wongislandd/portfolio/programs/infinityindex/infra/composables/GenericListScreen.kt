package com.wongislandd.portfolio.programs.infinityindex.infra.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Checkbox
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import com.wongislandd.portfolio.programs.infinityindex.infra.DetailsUiEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.ListUiEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityModel
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import com.wongislandd.portfolio.programs.infinityindex.infra.util.SelectableSortOption
import com.wongislandd.portfolio.programs.infinityindex.infra.util.SortOption
import com.wongislandd.portfolio.programs.infinityindex.infra.util.isDefaultSelectionSorted
import com.wongislandd.portfolio.programs.infinityindex.infra.util.isNoSortOptionSelected
import com.wongislandd.portfolio.programs.infinityindex.infra.util.sendEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.BaseListViewModel
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.ComicsListScreenStateSlice
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

data class RelatedEntityListConfiguration(
    val rootEntityType: EntityType,
    val rootEntityId: Int,
    val relatedEntityType: EntityType,
    val topBarTitle: String? = null
)

@OptIn(KoinExperimentalAPI::class)
@Composable
inline fun <NETWORK_TYPE, reified T : BaseListViewModel<NETWORK_TYPE, out EntityModel>> GenericListScreen(
    relatedListConfig: RelatedEntityListConfiguration? = null
) {
    val viewModel = koinViewModel<T>()

    // Signal to related plugins to look for these results
    relatedListConfig?.let {
        LaunchedEffect(relatedListConfig) {
            viewModel.uiEventBus.sendEvent(
                DetailsUiEvent.RelatedListInitialized(
                    relatedListConfig.rootEntityId,
                    relatedListConfig.rootEntityType
                )
            )
        }
    }

    val screenState by viewModel.screenStateSlice.listState.collectAsState()
    val lazyPagingEntities = viewModel.screenStateSlice.listPagingData.collectAsLazyPagingItems()
    val comicListScreenState = if (viewModel.screenStateSlice is ComicsListScreenStateSlice) {
        viewModel.screenStateSlice.screenState.collectAsState()
    } else {
        null
    }
    val coroutineScope = rememberCoroutineScope()
    Scaffold(topBar = {
        GlobalTopAppBar(
            title = relatedListConfig?.topBarTitle
                ?: viewModel.screenStateSlice.entityType.displayName,
            isTitleShown = !screenState.searchState.isSearchBoxVisible,
            actions = {
                ExpandingSearch(
                    isExpanded = screenState.searchState.isSearchBoxVisible,
                    currentSearchParam = screenState.searchState.searchQuery,
                    onSearchCleared = {
                        coroutineScope.sendEvent(
                            viewModel.uiEventBus,
                            ListUiEvent.ClearSearchQuery
                        )
                    },
                    onSearchParamChanged = { newQuery ->
                        coroutineScope.sendEvent(
                            viewModel.uiEventBus,
                            ListUiEvent.SetPendingSearchQuery(newQuery)
                        )
                    },
                    onSearchParamSubmitted = {
                        coroutineScope.sendEvent(
                            viewModel.uiEventBus,
                            ListUiEvent.SubmitSearchQuery(it)
                        )
                    },
                    onSearchIconClicked = {
                        coroutineScope.sendEvent(
                            viewModel.uiEventBus,
                            ListUiEvent.SearchClicked
                        )
                    },
                )
                // Enable filters for comics
                comicListScreenState?.also { comicListScreenState ->
                    ComicFilters(
                        isVariantsFilterEnabled = comicListScreenState.value.isVariantsEnabled,
                        isDigitalAvailabilityFilterEnabled = comicListScreenState.value.isDigitallyAvailableFilterEnabled,
                        onVariantsFilterChanged = {
                            coroutineScope.sendEvent(
                                viewModel.uiEventBus,
                                ListUiEvent.ToggleVariantsFilter(it)
                            )
                        },
                        onDigitalAvailabilityFilterChanged = {
                            coroutineScope.sendEvent(
                                viewModel.uiEventBus,
                                ListUiEvent.ToggleDigitalAvailabilityFilter(it)
                            )
                        },
                    )
                }
                SortSelection(screenState.availableSortOptions, onSortSelected = {
                    coroutineScope.sendEvent(viewModel.uiEventBus, ListUiEvent.SortSelected(it))
                })
            }
        )
    }) {
        Box(modifier = Modifier.fillMaxSize()) {
            EntityList(lazyPagingEntities)
        }
    }
}

@Composable
fun ComicFilters(
    isVariantsFilterEnabled: Boolean,
    isDigitalAvailabilityFilterEnabled: Boolean,
    onVariantsFilterChanged: (Boolean) -> Unit,
    onDigitalAvailabilityFilterChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    Column(modifier = modifier) {
        IconButton(
            onClick = { expanded = true },
        ) {
            Icon(
                imageVector = Filter,
                tint = if (isDigitalAvailabilityFilterEnabled || isVariantsFilterEnabled) {
                    MaterialTheme.colors.secondary
                } else {
                    MaterialTheme.colors.onPrimary
                },
                contentDescription = "Filter Options",
                modifier = Modifier.size(24.dp)
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            LabeledCheckbox(
                checked = isDigitalAvailabilityFilterEnabled,
                onCheckedChange = onDigitalAvailabilityFilterChanged,
                label = "Digitally Available"
            )
            LabeledCheckbox(
                checked = isVariantsFilterEnabled,
                onCheckedChange = onVariantsFilterChanged,
                label = "Allow Variants"
            )
        }
    }
}

@Composable
private fun LabeledCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    label: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(8.dp).clickable { onCheckedChange(!checked) }
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}

@Composable
fun ExpandingSearch(
    currentSearchParam: String,
    onSearchCleared: () -> Unit,
    onSearchParamChanged: (String) -> Unit,
    onSearchParamSubmitted: (String) -> Unit,
    onSearchIconClicked: () -> Unit,
    isExpanded: Boolean,
    modifier: Modifier = Modifier
) {
    val searchBoxFocusRequester = remember { FocusRequester() }
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isExpanded) {
            LaunchedEffect(isExpanded) {
                searchBoxFocusRequester.requestFocus()
            }

            TextField(
                value = currentSearchParam,
                onValueChange = onSearchParamChanged,
                placeholder = { Text("Search...") },
                singleLine = true,
                leadingIcon = {
                    IconButton(onClick = onSearchCleared) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Clear Search",
                            tint = MaterialTheme.colors.onSurface
                        )
                    }
                },
                trailingIcon = {
                    IconButton(onClick = {
                        onSearchParamSubmitted(currentSearchParam)
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowForward,
                            contentDescription = "Submit Search",
                            tint = MaterialTheme.colors.onSurface
                        )
                    }
                },
                modifier = Modifier
                    .focusRequester(searchBoxFocusRequester) // Attach FocusRequester to TextField
                    .padding(horizontal = 16.dp),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = MaterialTheme.colors.onSurface,
                    backgroundColor = MaterialTheme.colors.surface,
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    onSearchParamSubmitted(
                        currentSearchParam
                    )
                    searchBoxFocusRequester.requestFocus()
                })
            )
        } else {
            IconButton(onClick = onSearchIconClicked) {
                Icon(
                    imageVector = Icons.Default.Search,
                    tint = if (currentSearchParam.isNotEmpty()) MaterialTheme.colors.secondary else MaterialTheme.colors.onPrimary,
                    contentDescription = "Search",
                )
            }
        }
    }
}

@Composable
fun SortSelection(
    sortOptions: List<SelectableSortOption>,
    onSortSelected: (SortOption) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    Column(modifier = modifier) {
        IconButton(
            onClick = { expanded = true },
        ) {
            Icon(
                imageVector = Sort,
                tint = if (sortOptions.isDefaultSelectionSorted() || sortOptions.isNoSortOptionSelected()) {
                    MaterialTheme.colors.onPrimary
                } else {
                    MaterialTheme.colors.secondary
                },
                contentDescription = "Sort",
                modifier = Modifier.size(24.dp)
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            sortOptions.forEach { selectableSortOption ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        onSortSelected(selectableSortOption.sortOption)
                    }
                ) {
                    Text(
                        text = selectableSortOption.sortOption.displayName,
                        color = if (selectableSortOption.isSelected) MaterialTheme.colors.primary else Color.Unspecified
                    )
                }
            }
        }
    }
}

@Composable
fun EntityList(
    pagedEntities: LazyPagingItems<out EntityModel>,
    modifier: Modifier = Modifier
) {
    PagingWrapper(pagedEntities, modifier,
        itemContent = { entity ->
            EntityCard(entity)
        },
        placeholderContent = {
            GhostEntityCard()
        },
        errorContent = { errorMsg ->
            GenericErrorScreen(errorMsg)
        },
        emptyContent = {
            Text("No results found")
        })
}
