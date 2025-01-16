package com.wongislandd.portfolio.programs.infinityindex.infra.di

import com.wongislandd.portfolio.programs.infinityindex.transformers.util.NetworkFieldTypeMapper
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val infraModule = module {
    singleOf(::NetworkFieldTypeMapper)
}