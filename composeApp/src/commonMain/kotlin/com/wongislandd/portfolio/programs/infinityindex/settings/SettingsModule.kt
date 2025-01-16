package com.wongislandd.portfolio.programs.infinityindex.settings

import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val settingsModule = module {
    viewModelOf(::SettingsViewModel)
    factoryOf(::SettingsScreenStateSlice)
}