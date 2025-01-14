package com.wongislandd.portfolio.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.wongislandd.nexus.navigation.GlobalTopAppBar

@Composable
fun PortfolioTopAppBar(
    title: String? = null,
    modifier: Modifier = Modifier,
    actions: (@Composable RowScope.() -> Unit) = {},
    content: @Composable () -> Unit
) {
    Scaffold(topBar = {
        GlobalTopAppBar(
            title = title, homeDestination =
            supportedNavigationItems[NavigationItemKey.LANDING_PAGE]?.completeRoute ?: throw IllegalStateException("Home destination not found"),
            actions = actions
        )
    }, modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }
    }
}