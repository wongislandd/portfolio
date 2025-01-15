package com.wongislandd.portfolio.desktop

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val desktopModule = module {
    factoryOf(::DesktopScreenStateSlice)
    factoryOf(::TaskbarScreenStateSlice)
    factoryOf(::WidgetProviderSlice)
    factoryOf(::WidgetManagementSlice)
}