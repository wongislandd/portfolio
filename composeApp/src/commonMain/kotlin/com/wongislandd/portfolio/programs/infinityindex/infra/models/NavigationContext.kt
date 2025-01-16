package com.wongislandd.portfolio.programs.infinityindex.infra.models

data class NavigationContext(
    val navRoute: String,
    val allowNavigation: Boolean = true
)