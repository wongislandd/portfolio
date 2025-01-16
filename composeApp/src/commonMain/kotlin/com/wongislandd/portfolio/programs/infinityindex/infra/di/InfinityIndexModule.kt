package com.wongislandd.portfolio.programs.infinityindex.infra.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.wongislandd.portfolio.programs.infinityindex.browse.browseModule
import com.wongislandd.portfolio.programs.infinityindex.infra.DetailsBackChannelEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.DetailsUiEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.transformers.LoadableImageTransformer
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.eventBusFactory
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.createDataStore
import com.wongislandd.portfolio.programs.infinityindex.repositories.DataStoreRepository
import com.wongislandd.portfolio.programs.infinityindex.settings.settingsModule
import com.wongislandd.portfolio.programs.infinityindex.transformers.util.DateTransformer
import com.wongislandd.portfolio.programs.infinityindex.transformers.util.EntityReferenceTransformer
import com.wongislandd.portfolio.programs.infinityindex.transformers.util.RoledCreatorTransformer
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::LoadableImageTransformer)
    singleOf(::DateTransformer)
    singleOf(::RoledCreatorTransformer)
    singleOf(::EntityReferenceTransformer)
    singleOf(::DataStoreRepository)
    eventBusFactory<DetailsBackChannelEvent>()
    eventBusFactory<DetailsUiEvent>()
}

fun dataStoreModule(context: Any?) = module {
    single<DataStore<Preferences>> { createDataStore(context) }
}

//val infraModule: List<Module> = listOf(networkModule, navigationModule, eventsModule)
fun infinityIndexModule(context: Any? = null) = listOf(
    dataStoreModule(context),
    appModule,
    infraModule, entitiesModule, browseModule,
    settingsModule
)