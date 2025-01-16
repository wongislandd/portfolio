package com.wongislandd.portfolio.programs.infinityindex.browse

import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val browseModule = module {
    factoryOf(::BrowseScreenStateSlice)
    viewModelOf(::BrowseViewModel)
}