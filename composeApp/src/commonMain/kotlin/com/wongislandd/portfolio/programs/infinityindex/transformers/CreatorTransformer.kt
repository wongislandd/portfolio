package com.wongislandd.portfolio.programs.infinityindex.transformers

import com.wongislandd.portfolio.programs.infinityindex.transformers.util.DateTransformer
import com.wongislandd.portfolio.programs.infinityindex.transformers.util.RelatedLinksTransformer
import com.wongislandd.portfolio.programs.infinityindex.infra.models.DefaultImageType
import com.wongislandd.portfolio.programs.infinityindex.infra.models.NavigationContext
import com.wongislandd.portfolio.programs.infinityindex.infra.navigation.NavigationHelper
import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.getAvailableItems
import com.wongislandd.portfolio.programs.infinityindex.infra.transformers.DataWrapperTransformer
import com.wongislandd.portfolio.programs.infinityindex.infra.transformers.LoadableImageTransformer
import com.wongislandd.portfolio.programs.infinityindex.infra.transformers.LoadableImageTransformerInput
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import com.wongislandd.portfolio.programs.infinityindex.infra.util.safeLet
import com.wongislandd.portfolio.programs.infinityindex.models.local.Creator
import com.wongislandd.portfolio.programs.infinityindex.models.network.NetworkCreator

class CreatorTransformer(
    private val loadableImageTransformer: LoadableImageTransformer,
    private val dateTransformer: DateTransformer,
    private val relatedLinksTransformer: RelatedLinksTransformer
) : DataWrapperTransformer<NetworkCreator, Creator>() {
    override fun itemTransformer(input: NetworkCreator): Creator? {
        val relatedLinks = input.urls?.let {
            relatedLinksTransformer.transform(it)
        } ?: emptyList()
        return safeLet(
            input.id,
            input.fullName,
            input.modified?.let { dateTransformer.transform(it) }
        ) { id, name, modified ->
            Creator(
                id = id,
                displayName = name,
                navContext = NavigationContext(
                    NavigationHelper.getDetailsRoute(
                        EntityType.CREATORS,
                        id,
                        name
                    )
                ),
                image = loadableImageTransformer.transform(
                    LoadableImageTransformerInput(
                        networkImage = input.thumbnail,
                        defaultImageType = DefaultImageType.PERSON
                    ),
                ),
                relatedEventsCount = input.events.getAvailableItems(),
                relatedStoriesCount = input.stories.getAvailableItems(),
                relatedCharactersCount = 0,
                relatedCreatorsCount = 0,
                relatedSeriesCount = input.series.getAvailableItems(),
                relatedComicsCount = input.comics.getAvailableItems(),
                lastModified = modified,
                relatedLinks = relatedLinks
            )
        }
    }
}