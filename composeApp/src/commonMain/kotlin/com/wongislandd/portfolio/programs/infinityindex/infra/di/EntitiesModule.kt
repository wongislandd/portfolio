package com.wongislandd.portfolio.programs.infinityindex.infra.di

import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.CharacterDetailsScreenStateSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.CharacterDetailsViewModel
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.ComicDetailsScreenStateSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.ComicDetailsViewModel
import com.wongislandd.portfolio.programs.infinityindex.transformers.util.RelatedDatesTransformer
import com.wongislandd.portfolio.programs.infinityindex.transformers.util.RelatedLinksTransformer
import com.wongislandd.portfolio.programs.infinityindex.transformers.util.RelatedPricesTransformer
import com.wongislandd.portfolio.programs.infinityindex.transformers.util.RelatedTextsTransformer
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.CreatorDetailsScreenStateSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.CreatorDetailsViewModel
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.EventDetailsScreenStateSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.EventDetailsViewModel
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.SeriesDetailsScreenStateSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.SeriesDetailsViewModel
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.StoriesListScreenStateSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.StoryDetailsViewModel
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.BaseDetailsScreenStateSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.ComicsListScreenStateSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.ComicSeriesSupplementaryEntityResolutionSlice
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.SearchSlice
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.SortSlice
import com.wongislandd.portfolio.programs.infinityindex.models.local.Character
import com.wongislandd.portfolio.programs.infinityindex.models.local.Comic
import com.wongislandd.portfolio.programs.infinityindex.models.local.Creator
import com.wongislandd.portfolio.programs.infinityindex.models.local.Event
import com.wongislandd.portfolio.programs.infinityindex.models.local.Series
import com.wongislandd.portfolio.programs.infinityindex.models.local.Story
import com.wongislandd.portfolio.programs.infinityindex.repositories.CachingPreferenceRepository
import com.wongislandd.portfolio.programs.infinityindex.repositories.CharactersEntityRepository
import com.wongislandd.portfolio.programs.infinityindex.repositories.ComicsEntityRepository
import com.wongislandd.portfolio.programs.infinityindex.repositories.CreatorsEntityRepository
import com.wongislandd.portfolio.programs.infinityindex.repositories.EventsEntityRepository
import com.wongislandd.portfolio.programs.infinityindex.repositories.SeriesEntityRepository
import com.wongislandd.portfolio.programs.infinityindex.repositories.StoriesEntityRepository
import com.wongislandd.portfolio.programs.infinityindex.transformers.CharacterTransformer
import com.wongislandd.portfolio.programs.infinityindex.transformers.ComicTransformer
import com.wongislandd.portfolio.programs.infinityindex.transformers.CreatorTransformer
import com.wongislandd.portfolio.programs.infinityindex.transformers.EventTransformer
import com.wongislandd.portfolio.programs.infinityindex.transformers.SeriesTransformer
import com.wongislandd.portfolio.programs.infinityindex.transformers.StoryTransformer
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.RelatedCharactersListViewModel
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.RelatedCharactersPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.RelatedComicsListViewModel
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.RelatedComicsPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.RelatedCreatorsListViewModel
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.RelatedCreatorsPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.RelatedEventsListViewModel
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.RelatedEventsPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.RelatedSeriesListViewModel
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.RelatedSeriesPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.RelatedStoriesListViewModel
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.RelatedStoriesPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.AllCharactersListViewModel
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.AllCharactersPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.AllComicsListViewModel
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.AllComicsPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.AllCreatorsListViewModel
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.AllCreatorsPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.AllEventsListViewModel
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.AllEventsPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.AllSeriesListViewModel
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.AllSeriesPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.AllStoriesListViewModel
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.AllStoriesPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.CharactersListScreenStateSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.CreatorsListScreenStateSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.EventsListScreenStateSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.SeriesListScreenStateSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.StoryDetailsScreenStateSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.SingleCharacterSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.SingleComicSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.SingleCreatorSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.SingleEventSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.SingleSeriesSlice
import com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices.SingleStorySlice
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val entitiesModule = module {
    // Shared
    factoryOf(::SortSlice)
    factoryOf(::SearchSlice)
    singleOf(::CachingPreferenceRepository)


    // Event-related
    factoryOf<BaseDetailsScreenStateSlice<Event>>(::EventDetailsScreenStateSlice)
    factoryOf(::EventsListScreenStateSlice)
    factoryOf(::AllEventsPagingSlice)
    factoryOf(::RelatedEventsPagingSlice)
    factoryOf(::SingleEventSlice)
    singleOf(::EventTransformer)
    singleOf(::EventsEntityRepository)
    viewModelOf(::EventDetailsViewModel)
    viewModelOf(::AllEventsListViewModel)
    viewModelOf(::RelatedEventsListViewModel)


    // Comic-related
    factoryOf<BaseDetailsScreenStateSlice<Comic>>(::ComicDetailsScreenStateSlice)
    factoryOf(::ComicsListScreenStateSlice)
    factoryOf(::RelatedComicsPagingSlice)
    factoryOf(::AllComicsPagingSlice)
    factoryOf(::SingleComicSlice)
    singleOf(::ComicTransformer)
    singleOf(::ComicsEntityRepository)
    viewModelOf(::ComicDetailsViewModel)
    viewModelOf(::AllComicsListViewModel)
    viewModelOf(::RelatedComicsListViewModel)

    // Creator-related
    factoryOf<BaseDetailsScreenStateSlice<Creator>>(::CreatorDetailsScreenStateSlice)
    factoryOf(::CreatorsListScreenStateSlice)
    factoryOf(::RelatedCreatorsPagingSlice)
    factoryOf(::AllCreatorsPagingSlice)
    factoryOf(::SingleCreatorSlice)
    singleOf(::CreatorTransformer)
    singleOf(::CreatorsEntityRepository)
    viewModelOf(::CreatorDetailsViewModel)
    viewModelOf(::AllCreatorsListViewModel)
    viewModelOf(::RelatedCreatorsListViewModel)

    // Story-related
    factoryOf<BaseDetailsScreenStateSlice<Story>>(::StoryDetailsScreenStateSlice)
    factoryOf(::StoriesListScreenStateSlice)
    factoryOf(::RelatedStoriesPagingSlice)
    factoryOf(::AllStoriesPagingSlice)
    factoryOf(::SingleStorySlice)
    singleOf(::StoryTransformer)
    singleOf(::StoriesEntityRepository)
    viewModelOf(::StoryDetailsViewModel)
    viewModelOf(::AllStoriesListViewModel)
    viewModelOf(::RelatedStoriesListViewModel)

    // Series-related
    factoryOf<BaseDetailsScreenStateSlice<Series>>(::SeriesDetailsScreenStateSlice)
    factoryOf(::SeriesListScreenStateSlice)
    factoryOf(::RelatedSeriesPagingSlice)
    factoryOf(::SingleSeriesSlice)
    factoryOf(::AllSeriesPagingSlice)
    factoryOf(::ComicSeriesSupplementaryEntityResolutionSlice)

    singleOf(::SeriesTransformer)
    singleOf(::SeriesEntityRepository)
    viewModelOf(::SeriesDetailsViewModel)
    viewModelOf(::AllSeriesListViewModel)
    viewModelOf(::RelatedSeriesListViewModel)

    // Character-related
    factoryOf<BaseDetailsScreenStateSlice<Character>>(::CharacterDetailsScreenStateSlice)
    factoryOf(::CharactersListScreenStateSlice)
    factoryOf(::RelatedCharactersPagingSlice)
    factoryOf(::AllCharactersPagingSlice)
    factoryOf(::SingleCharacterSlice)
    singleOf(::CharacterTransformer)
    singleOf(::CharactersEntityRepository)
    viewModelOf(::CharacterDetailsViewModel)
    viewModelOf(::AllCharactersListViewModel)
    viewModelOf(::RelatedCharactersListViewModel)

    // Other transformers and singletons
    singleOf(::RelatedDatesTransformer)
    singleOf(::RelatedLinksTransformer)
    singleOf(::RelatedPricesTransformer)
    singleOf(::RelatedTextsTransformer)

}