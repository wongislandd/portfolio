package com.wongislandd.portfolio.desktop

import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val desktopModule = module {
    viewModelOf(::DesktopViewModel)
    factoryOf(::DesktopScreenStateSlice)
    factoryOf(::TaskbarScreenStateSlice)
    factoryOf(::WidgetProviderSlice)
    factoryOf(::ActiveWidgetsSlice)
}