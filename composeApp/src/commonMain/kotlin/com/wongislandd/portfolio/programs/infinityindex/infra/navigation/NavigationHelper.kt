package com.wongislandd.portfolio.programs.infinityindex.infra.navigation

import ID_ARG
import NavigationItem
import ROOT_ENTITY_ARG
import ROOT_ENTITY_ID_ARG
import TITLE_ARG
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType

object NavigationHelper {

    fun getRelatedListRoute(
        rootEntityType: EntityType,
        rootEntityId: Int,
        relatedEntityType: EntityType,
        title: String?
    ): String {
        val navigationItem = when (relatedEntityType) {
            EntityType.CHARACTERS -> {
                NavigationItem.RelatedCharactersListScreen
            }

            EntityType.COMICS -> {
                NavigationItem.RelatedComicListScreen
            }

            EntityType.CREATORS -> {
                NavigationItem.RelatedCreatorsListScreen
            }

            EntityType.EVENTS -> {
                NavigationItem.RelatedEventsListScreen
            }

            EntityType.SERIES -> {
                NavigationItem.RelatedSeriesListScreen
            }

            EntityType.STORIES -> {
                NavigationItem.RelatedStoriesListScreen
            }
        }
        val route = navigationItem.reconstructRoute(
            mapOf(
                ROOT_ENTITY_ARG to rootEntityType.name,
                ROOT_ENTITY_ID_ARG to rootEntityId.toString(),
                TITLE_ARG to cleanupTitle(title)
            )
        )
        return route
    }

    fun getAllListRoute(entityType: EntityType): String {
        val navigationItem = when (entityType) {
            EntityType.CHARACTERS -> {
                NavigationItem.AllCharacterListScreen
            }

            EntityType.COMICS -> {
                NavigationItem.AllComicListScreen
            }

            EntityType.CREATORS -> {
                NavigationItem.AllCreatorListScreen
            }

            EntityType.EVENTS -> {
                NavigationItem.AllEventListScreen
            }

            EntityType.SERIES -> {
                NavigationItem.AllSeriesListScreen
            }

            EntityType.STORIES -> {
                NavigationItem.AllStoriesListScreen
            }
        }
        return navigationItem.route
    }

    fun getDetailsRoute(entityType: EntityType, id: Int, title: String? = null): String {
        val navigationItem = when (entityType) {
            EntityType.CHARACTERS -> {
                NavigationItem.CharacterDetailsScreen
            }

            EntityType.COMICS -> {
                NavigationItem.ComicDetailsScreen
            }

            EntityType.CREATORS -> {
                NavigationItem.CreatorDetailsScreen
            }

            EntityType.EVENTS -> {
                NavigationItem.EventDetailsScreen
            }

            EntityType.SERIES -> {
                NavigationItem.SeriesDetailsScreen
            }

            EntityType.STORIES -> {
                NavigationItem.StoryDetailsScreen
            }
        }
        val route = navigationItem.reconstructRoute(
            mapOf(
                ID_ARG to id,
                TITLE_ARG to cleanupTitle(title)
            )
        )
        return route
    }

    fun getBrowseRoute(): String {
        return NavigationItem.Browse.route
    }

    fun getSettingsRoute(): String {
        return NavigationItem.Settings.route
    }
}

/**
 * Navigation gets weird if there is a "/" inside of the title, messing with the route.
 */
private fun cleanupTitle(title: String?): String? {
    return title?.replace("/", " ")
}