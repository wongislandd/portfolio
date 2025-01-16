package com.wongislandd.portfolio.programs.infinityindex.infra.navigation

import ID_ARG
import NavigationItem
import NavigationItemType
import ROOT_ENTITY_ARG
import ROOT_ENTITY_ID_ARG
import TITLE_ARG
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.wongislandd.portfolio.programs.infinityindex.browse.BrowseScreen
import com.wongislandd.portfolio.programs.infinityindex.browse.InfinityIndexSplashScreen
import com.wongislandd.portfolio.programs.infinityindex.infra.composables.GenericDetailsScreen
import com.wongislandd.portfolio.programs.infinityindex.infra.composables.GenericListScreen
import com.wongislandd.portfolio.programs.infinityindex.infra.composables.RelatedEntityListConfiguration
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import com.wongislandd.portfolio.programs.infinityindex.models.network.NetworkCharacter
import com.wongislandd.portfolio.programs.infinityindex.models.network.NetworkComic
import com.wongislandd.portfolio.programs.infinityindex.models.network.NetworkCreator
import com.wongislandd.portfolio.programs.infinityindex.models.network.NetworkEvent
import com.wongislandd.portfolio.programs.infinityindex.models.network.NetworkSeries
import com.wongislandd.portfolio.programs.infinityindex.models.network.NetworkStory
import com.wongislandd.portfolio.programs.infinityindex.settings.SettingsScreen
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.AllCharactersListViewModel
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.AllComicsListViewModel
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.AllCreatorsListViewModel
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.AllEventsListViewModel
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.AllSeriesListViewModel
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.AllStoriesListViewModel
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.CharacterDetailsViewModel
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.ComicDetailsViewModel
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.CreatorDetailsViewModel
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.EventDetailsViewModel
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.RelatedCharactersListViewModel
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.RelatedComicsListViewModel
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.RelatedCreatorsListViewModel
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.RelatedEventsListViewModel
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.RelatedSeriesListViewModel
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.RelatedStoriesListViewModel
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.SeriesDetailsViewModel
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.StoryDetailsViewModel

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = NavigationItem.Browse.route,
) {
    val navController = LocalNavHostController.current
    val pageTurnEnterTransition = slideInHorizontally(
        initialOffsetX = { it },
        animationSpec = tween(700)
    )

    val pageTurnExitTransition = slideOutHorizontally(
        targetOffsetX = { -it },
        animationSpec = tween(700)
    )

    val pageReturnEnterTransition = slideInHorizontally(
        initialOffsetX = { -it },
        animationSpec = tween(700)
    )

    val pageReturnExitTransition = slideOutHorizontally(
        targetOffsetX = { it },
        animationSpec = tween(700)
    )

    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = {
            pageTurnEnterTransition
        },
        exitTransition = {
            pageTurnExitTransition
        },
        popEnterTransition = {
            pageReturnEnterTransition
        },
        popExitTransition = {
            pageReturnExitTransition
        },
        modifier = modifier
    ) {
        NavigationItem.entries.forEach { navItem ->
            when (navItem.type) {
                NavigationItemType.ALL -> {
                    composable(route = navItem.route) {
                        when (navItem) {
                            NavigationItem.AllComicListScreen -> GenericListScreen<NetworkComic, AllComicsListViewModel>()
                            NavigationItem.AllCreatorListScreen -> GenericListScreen<NetworkCreator, AllCreatorsListViewModel>()
                            NavigationItem.AllCharacterListScreen -> GenericListScreen<NetworkCharacter, AllCharactersListViewModel>()
                            NavigationItem.AllSeriesListScreen -> GenericListScreen<NetworkSeries, AllSeriesListViewModel>()
                            NavigationItem.AllEventListScreen -> GenericListScreen<NetworkEvent, AllEventsListViewModel>()
                            NavigationItem.AllStoriesListScreen -> GenericListScreen<NetworkStory, AllStoriesListViewModel>()
                            else -> throw IllegalStateException()
                        }
                    }
                }

                NavigationItemType.DETAILS -> {
                    composable(
                        route = navItem.route,
                        arguments = listOf(
                            navArgument(ID_ARG) { type = NavType.IntType },
                            navArgument(TITLE_ARG) { type = NavType.StringType; nullable = true }
                        )
                    ) { backStackEntry ->
                        val primaryId = backStackEntry.arguments?.getInt(ID_ARG)
                            ?: throw IllegalArgumentException("Missing ID argument")
                        val title = backStackEntry.arguments?.getString(TITLE_ARG)
                        when (navItem) {
                            NavigationItem.ComicDetailsScreen -> GenericDetailsScreen<ComicDetailsViewModel>(
                                primaryId,
                                title
                            )

                            NavigationItem.CreatorDetailsScreen -> GenericDetailsScreen<CreatorDetailsViewModel>(
                                primaryId,
                                title
                            )

                            NavigationItem.CharacterDetailsScreen -> GenericDetailsScreen<CharacterDetailsViewModel>(
                                primaryId,
                                title
                            )

                            NavigationItem.SeriesDetailsScreen -> GenericDetailsScreen<SeriesDetailsViewModel>(
                                primaryId,
                                title
                            )

                            NavigationItem.EventDetailsScreen -> GenericDetailsScreen<EventDetailsViewModel>(
                                primaryId,
                                title
                            )

                            NavigationItem.StoryDetailsScreen -> GenericDetailsScreen<StoryDetailsViewModel>(
                                primaryId,
                                title
                            )

                            else -> throw IllegalStateException()
                        }
                    }
                }

                NavigationItemType.RELATED -> {
                    composable(
                        route = navItem.route,
                        arguments = listOf(
                            navArgument(ROOT_ENTITY_ARG) { type = NavType.StringType },
                            navArgument(ROOT_ENTITY_ID_ARG) { type = NavType.IntType },
                            navArgument(TITLE_ARG) { type = NavType.StringType }
                        )
                    ) { backStackEntry ->
                        val rootEntity = backStackEntry.arguments?.getString(ROOT_ENTITY_ARG)
                            ?: throw IllegalArgumentException("Missing root entity argument")
                        val rootEntityId = backStackEntry.arguments?.getInt(ROOT_ENTITY_ID_ARG)
                            ?: throw IllegalArgumentException("Missing root entity ID argument")
                        val title = backStackEntry.arguments?.getString(TITLE_ARG)

                        when (navItem) {
                            NavigationItem.RelatedComicListScreen -> {
                                GenericListScreen<NetworkComic, RelatedComicsListViewModel>(
                                    getConfig(
                                        rootEntity,
                                        rootEntityId,
                                        EntityType.COMICS.name,
                                        title
                                    )
                                )
                            }

                            NavigationItem.RelatedCharactersListScreen -> {
                                GenericListScreen<NetworkCharacter, RelatedCharactersListViewModel>(
                                    getConfig(
                                        rootEntity,
                                        rootEntityId,
                                        EntityType.CHARACTERS.name,
                                        title
                                    )
                                )
                            }

                            NavigationItem.RelatedCreatorsListScreen -> {
                                GenericListScreen<NetworkCreator, RelatedCreatorsListViewModel>(
                                    getConfig(
                                        rootEntity,
                                        rootEntityId,
                                        EntityType.CREATORS.name,
                                        title
                                    )
                                )
                            }

                            NavigationItem.RelatedSeriesListScreen -> {
                                GenericListScreen<NetworkSeries, RelatedSeriesListViewModel>(
                                    getConfig(
                                        rootEntity,
                                        rootEntityId,
                                        EntityType.SERIES.name,
                                        title
                                    )
                                )
                            }

                            NavigationItem.RelatedEventsListScreen -> {
                                GenericListScreen<NetworkEvent, RelatedEventsListViewModel>(
                                    getConfig(
                                        rootEntity,
                                        rootEntityId,
                                        EntityType.EVENTS.name,
                                        title
                                    )
                                )
                            }

                            NavigationItem.RelatedStoriesListScreen -> {
                                GenericListScreen<NetworkStory, RelatedStoriesListViewModel>(
                                    getConfig(
                                        rootEntity,
                                        rootEntityId,
                                        EntityType.STORIES.name,
                                        title
                                    )
                                )
                            }

                            else -> throw IllegalStateException()
                        }
                    }
                }

                NavigationItemType.OTHER -> {
                    composable(route = navItem.route) {
                        when (navItem) {
                            NavigationItem.Splash -> {
                                InfinityIndexSplashScreen()
                            }
                            NavigationItem.Browse -> {
                                BrowseScreen()
                            }
                            NavigationItem.Settings -> {
                                SettingsScreen()
                            }
                            else -> BrowseScreen()
                        }
                    }
                }
            }
        }
    }
}

private fun getConfig(
    rootEntityType: String,
    rootEntityId: Int,
    relatedEntityType: String,
    title: String?
): RelatedEntityListConfiguration {
    return RelatedEntityListConfiguration(
        EntityType.valueOf(rootEntityType),
        rootEntityId,
        EntityType.valueOf(relatedEntityType),
        title
    )
}