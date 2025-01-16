package com.wongislandd.portfolio.programs.infinityindex.transformers

import com.wongislandd.portfolio.programs.infinityindex.infra.transformers.LoadableImageTransformer
import com.wongislandd.portfolio.programs.infinityindex.models.local.Character
import com.wongislandd.portfolio.programs.infinityindex.models.network.NetworkCharacter
import com.wongislandd.portfolio.programs.infinityindex.transformers.util.RelatedLinksTransformer
import com.wongislandd.portfolio.programs.infinityindex.transformers.util.DateTransformer
import com.wongislandd.portfolio.programs.infinityindex.infra.models.DefaultImageType
import com.wongislandd.portfolio.programs.infinityindex.infra.models.NavigationContext
import com.wongislandd.portfolio.programs.infinityindex.infra.navigation.NavigationHelper
import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.getAvailableItems
import com.wongislandd.portfolio.programs.infinityindex.infra.transformers.DataWrapperTransformer
import com.wongislandd.portfolio.programs.infinityindex.infra.transformers.LoadableImageTransformerInput
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import com.wongislandd.portfolio.programs.infinityindex.infra.util.dropIfEmpty
import com.wongislandd.portfolio.programs.infinityindex.infra.util.safeLet

class CharacterTransformer(
    private val loadableImageTransformer: LoadableImageTransformer,
    private val relatedLinksTransformer: RelatedLinksTransformer,
    private val dateTransformer: DateTransformer
) : DataWrapperTransformer<NetworkCharacter, Character>() {
    override fun itemTransformer(input: NetworkCharacter): Character? {
        val relatedLinks = input.urls?.let {
            relatedLinksTransformer.transform(it)
        } ?: emptyList()
        return safeLet(
            input.id,
            input.name,
            input.modified?.let { dateTransformer.transform(it) }
        ) { id, name, modified ->
            Character(
                id = id,
                displayName = name,
                image = loadableImageTransformer.transform(
                    LoadableImageTransformerInput(
                        networkImage = input.thumbnail,
                        defaultImageType = DefaultImageType.PERSON
                    )
                ),
                navContext = NavigationContext(
                    NavigationHelper.getDetailsRoute(
                        EntityType.CHARACTERS,
                        id,
                        name
                    )
                ),
                description = input.description.dropIfEmpty(),
                modified = input.modified.dropIfEmpty(),
                relatedLinks = relatedLinks,
                relatedEventsCount = input.events.getAvailableItems(),
                relatedStoriesCount = input.stories.getAvailableItems(),
                relatedCharactersCount = 0,
                relatedCreatorsCount = 0,
                relatedSeriesCount = input.series.getAvailableItems(),
                relatedComicsCount = input.comics.getAvailableItems(),
                lastModified = modified
            )
        }
    }
}