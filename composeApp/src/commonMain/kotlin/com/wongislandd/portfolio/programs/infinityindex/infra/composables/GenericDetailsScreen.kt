package com.wongislandd.portfolio.programs.infinityindex.infra.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wongislandd.portfolio.programs.infinityindex.infra.DetailsUiEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.navigation.NavigationHelper
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityModel
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import com.wongislandd.portfolio.programs.infinityindex.infra.util.Resource
import com.wongislandd.portfolio.programs.infinityindex.infra.util.getEntityType
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.BaseDetailsScreenState
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.BaseDetailsViewModel
import com.wongislandd.portfolio.programs.infinityindex.models.local.Character
import com.wongislandd.portfolio.programs.infinityindex.models.local.Comic
import com.wongislandd.portfolio.programs.infinityindex.models.local.Creator
import com.wongislandd.portfolio.programs.infinityindex.models.local.Event
import com.wongislandd.portfolio.programs.infinityindex.models.local.Series
import com.wongislandd.portfolio.programs.infinityindex.models.local.Story
import com.wongislandd.portfolio.programs.infinityindex.ui.CharacterDetails
import com.wongislandd.portfolio.programs.infinityindex.ui.ComicDetails
import com.wongislandd.portfolio.programs.infinityindex.ui.CreatorDetails
import com.wongislandd.portfolio.programs.infinityindex.ui.EventDetails
import com.wongislandd.portfolio.programs.infinityindex.ui.SeriesDetails
import com.wongislandd.portfolio.programs.infinityindex.ui.StoryDetails
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
inline fun <reified T : BaseDetailsViewModel<out EntityModel>> GenericDetailsScreen(
    primaryId: Int,
    title: String?,
    modifier: Modifier = Modifier,
) {
    val viewModel = koinViewModel<T>()
    LaunchedEffect(Unit) {
        viewModel.uiEventBus.sendEvent(
            DetailsUiEvent.PageInitialized(
                primaryId,
                viewModel.entityType
            )
        )
    }
    val screenState by viewModel.screenStateSlice.screenState.collectAsState()
    Scaffold(modifier = modifier, topBar = {
        GlobalTopAppBar(
            title = title
        )
    }) {
        Box(modifier = Modifier.fillMaxSize()) {
            when (val primaryRes = screenState.primaryRes) {
                is Resource.Success -> {
                    DetailsContents(
                        primaryRes.data,
                        screenState,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                is Resource.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is Resource.Error -> {
                    GenericErrorScreen(
                        errorMessage = primaryRes.error?.displayMessage ?: "Error loading details",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                is Resource.NotLoading -> {
                    // do nothing
                }
            }
        }
    }
}

@Composable
fun DetailsContents(
    primaryModel: EntityModel,
    screenState: BaseDetailsScreenState<out EntityModel>,
    modifier: Modifier = Modifier
) {
    val supplementaryData = screenState.supplementaryEntityData.collectAsState()
    val supplementaryDataValue = supplementaryData.value
    AdditionalDetailsLazyColumn(modifier = modifier) {
        item {
            Column(
                modifier = Modifier.widthIn(max = 1000.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PrimaryDetailContents(
                    primaryModel,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }

        // Support showing supplementary data
        item {
            when (supplementaryDataValue) {
                is Resource.Loading -> {
                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                        EntitySectionHeader(
                            entityType = EntityType.SERIES,
                            totalEntityCount = null,
                            showAllRoute = null,
                            isContentInitializing = true
                        )
                        GhostEntityCard()
                    }
                }

                is Resource.Success -> {
                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                        EntitySectionHeader(
                            entityType = supplementaryDataValue.data.entity.getEntityType(),
                            totalEntityCount = null,
                            showAllRoute = null,
                            title = supplementaryDataValue.data.title
                        )
                        EntityCard(supplementaryDataValue.data.entity)
                    }
                }

                is Resource.Error,
                Resource.NotLoading -> {
                    // do nothing
                }
            }
        }

        item {
            ListOfEntities(
                screenState,
                showAllRouteGetter = { entityType ->
                    // Show all related to the supplementary data (useful for comics showing on comics screen from series resolution)
                    if (supplementaryDataValue is Resource.Success && entityType == primaryModel.getEntityType()) {
                        NavigationHelper.getRelatedListRoute(
                            supplementaryDataValue.data.entity.getEntityType(),
                            supplementaryDataValue.data.entity.id,
                            entityType,
                            "${entityType.displayName} related to ${supplementaryDataValue.data.entity.displayName}"
                        )
                    } else {
                        NavigationHelper.getRelatedListRoute(
                            primaryModel.getEntityType(),
                            primaryModel.id,
                            entityType,
                            "${entityType.displayName} related to ${primaryModel.displayName}"
                        )
                    }
                },
                useCase = EntitiesListUseCase.DETAILS,
            )
        }
        item {
            LastModifiedLabel(primaryModel)
            MarvelAttributionTextLabel()
        }
    }
}

@Composable
private fun PrimaryDetailContents(primaryModel: EntityModel, modifier: Modifier = Modifier) {
    when (primaryModel) {
        is Comic -> {
            ComicDetails(primaryModel, modifier)
        }

        is Creator -> {
            CreatorDetails(primaryModel, modifier)
        }

        is Character -> {
            CharacterDetails(primaryModel, modifier)
        }

        is Story -> {
            StoryDetails(primaryModel, modifier)
        }

        is Series -> {
            SeriesDetails(primaryModel, modifier)
        }

        is Event -> {
            EventDetails(primaryModel, modifier)
        }
    }
}