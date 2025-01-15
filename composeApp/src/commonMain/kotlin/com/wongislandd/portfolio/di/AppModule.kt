package com.wongislandd.portfolio.di

import com.wongislandd.nexus.di.infraModule
import com.wongislandd.nexus.navigation.NavigationItem
import com.wongislandd.nexus.navigation.NavigationSlice
import com.wongislandd.portfolio.AppViewModel
import com.wongislandd.portfolio.desktop.desktopModule
import com.wongislandd.portfolio.navigation.supportedNavigationItems
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

fun appModule(appContext: Any? = null) = module {
    viewModelOf(::AppViewModel)
    factoryOf(::NavigationSlice)
    single<Set<NavigationItem>> { supportedNavigationItems.values.toSet() }
}

fun initializeKoin(context: Any? = null) =
    startKoin {
        modules(*infraModule.toTypedArray(), appModule(context), desktopModule)
    }