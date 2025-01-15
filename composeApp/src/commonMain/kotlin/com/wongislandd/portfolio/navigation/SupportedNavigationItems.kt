package com.wongislandd.portfolio.navigation

import com.wongislandd.nexus.navigation.NavigationItem

val supportedNavigationItems = mutableMapOf(
    NavigationItemKey.LANDING_PAGE to NavigationItem(
        NavigationItemKey.LANDING_PAGE.name,
        "Christopher Wong's Portfolio",
        "home"
    ),
    NavigationItemKey.SANDBOX to NavigationItem(
        NavigationItemKey.SANDBOX.name,
        "Sandbox",
        "sandbox"
    )
)