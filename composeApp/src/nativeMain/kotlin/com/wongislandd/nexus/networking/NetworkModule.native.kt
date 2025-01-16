package com.wongislandd.nexus.networking

import com.wongislandd.portfolio.programs.infinityindex.infra.networking.createInfinityIndexHttpClient
import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.Module
import org.koin.dsl.module

actual val networkModule: Module = module {
    single<HttpClient> { createInfinityIndexHttpClient(Darwin.create()) }
}