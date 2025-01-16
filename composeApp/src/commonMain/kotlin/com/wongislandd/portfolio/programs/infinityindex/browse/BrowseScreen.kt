package com.wongislandd.portfolio.programs.infinityindex.browse

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wongislandd.portfolio.programs.infinityindex.infra.composables.EntitiesListUseCase
import com.wongislandd.portfolio.programs.infinityindex.infra.composables.GenericErrorScreen
import com.wongislandd.portfolio.programs.infinityindex.infra.composables.GlobalTopAppBar
import com.wongislandd.portfolio.programs.infinityindex.infra.composables.ListOfEntities
import com.wongislandd.portfolio.programs.infinityindex.infra.composables.MarvelAttributionTextLabel
import com.wongislandd.portfolio.programs.infinityindex.infra.navigation.LocalNavHostController
import com.wongislandd.portfolio.programs.infinityindex.infra.navigation.NavigationHelper
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun BrowseScreen(modifier: Modifier = Modifier) {
    val viewModel = koinViewModel<BrowseViewModel>()
    val screenState by viewModel.screenStateSlice.screenState.collectAsState()
    val navController = LocalNavHostController.current
    Box(modifier = modifier.fillMaxSize()) {
        Scaffold(topBar = {
            GlobalTopAppBar(showBackButton = false,
                actions = {
                    IconButton(
                        onClick = { navController.navigate(NavigationHelper.getSettingsRoute()) },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Go to settings",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                })
        }) {
            if (screenState.errorData != null) {
                GenericErrorScreen(
                    errorMessage = screenState.errorData.toString(),
                    modifier = Modifier.fillMaxSize().align(Alignment.Center)
                )
            } else {
                LazyColumn {
                    item {
                        ListOfEntities(
                            screenState,
                            showAllRouteGetter = { entityType ->
                                NavigationHelper.getAllListRoute(entityType)
                            },
                            useCase = EntitiesListUseCase.BROWSE
                        )
                    }
                    item {
                        MarvelAttributionTextLabel()
                    }
                }
            }
        }
        if (screenState.isBrowseScreenLoading) {
            InfinityIndexLoadingScreen(modifier = Modifier.clickable(
                indication = null, // Removes ripple or any click feedback
                interactionSource = remember { MutableInteractionSource() }
            ) { })
        }
    }
}