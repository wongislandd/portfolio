package com.wongislandd.portfolio

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wongislandd.nexus.navigation.LocalNavHostController
import com.wongislandd.portfolio.landingpage.LandingPage
import com.wongislandd.portfolio.navigation.NavigationItemKey
import com.wongislandd.portfolio.navigation.supportedNavigationItems

@Composable
fun PortfolioNavHost(
    modifier: Modifier = Modifier,
    startDestination: NavigationItemKey = NavigationItemKey.LANDING_PAGE
) {
    val navController = LocalNavHostController.current
    val startingDestination = supportedNavigationItems[startDestination]
        ?: throw IllegalStateException("Couldn't find registered start destination!")
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
        startDestination = startingDestination.completeRoute,
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
        supportedNavigationItems.map { (_, navigationItem) ->
            when (NavigationItemKey.valueOf(navigationItem.navigationKey)) {
                NavigationItemKey.LANDING_PAGE -> {
                    composable(route = navigationItem.completeRoute) {
                        LandingPage()
                    }
                }
            }
        }
    }
}