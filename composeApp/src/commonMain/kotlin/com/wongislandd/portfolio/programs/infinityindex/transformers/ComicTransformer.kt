package com.wongislandd.portfolio.programs.infinityindex.transformers

import com.wongislandd.portfolio.programs.infinityindex.models.util.TextType
import com.wongislandd.portfolio.programs.infinityindex.transformers.util.DateTransformer
import com.wongislandd.portfolio.programs.infinityindex.transformers.util.EntityReferenceTransformer
import com.wongislandd.portfolio.programs.infinityindex.transformers.util.RelatedDatesTransformer
import com.wongislandd.portfolio.programs.infinityindex.transformers.util.RelatedLinksTransformer
import com.wongislandd.portfolio.programs.infinityindex.transformers.util.RelatedPricesTransformer
import com.wongislandd.portfolio.programs.infinityindex.transformers.util.RelatedTextsTransformer
import com.wongislandd.portfolio.programs.infinityindex.transformers.util.RoledCreatorOutput
import com.wongislandd.portfolio.programs.infinityindex.transformers.util.RoledCreatorTransformer
import com.wongislandd.portfolio.programs.infinityindex.infra.models.DefaultImageType
import com.wongislandd.portfolio.programs.infinityindex.infra.models.NavigationContext
import com.wongislandd.portfolio.programs.infinityindex.infra.navigation.NavigationHelper
import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.getAvailableItems
import com.wongislandd.portfolio.programs.infinityindex.infra.transformers.DataWrapperTransformer
import com.wongislandd.portfolio.programs.infinityindex.infra.transformers.LoadableImageTransformer
import com.wongislandd.portfolio.programs.infinityindex.infra.transformers.LoadableImageTransformerInput
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import com.wongislandd.portfolio.programs.infinityindex.infra.util.dropIfEmpty
import com.wongislandd.portfolio.programs.infinityindex.infra.util.safeLet
import com.wongislandd.portfolio.programs.infinityindex.models.local.Comic
import com.wongislandd.portfolio.programs.infinityindex.models.network.NetworkComic

class ComicTransformer(
    private val loadableImageTransformer: LoadableImageTransformer,
    private val relatedDatesTransformer: RelatedDatesTransformer,
    private val relatedTextsTransformer: RelatedTextsTransformer,
    private val relatedLinksTransformer: RelatedLinksTransformer,
    private val relatedPricesTransformer: RelatedPricesTransformer,
    private val roledCreatorTransformer: RoledCreatorTransformer,
    private val entityReferenceTransformer: EntityReferenceTransformer,
    private val datesTransformer: DateTransformer
) : DataWrapperTransformer<NetworkComic, Comic>() {

    private val textTypesToCountAsDescription = setOf(
        TextType.ISSUE_SOLICIT_TEXT,
        TextType.ISSUE_PREVIEW_TEXT
    )

    override fun itemTransformer(input: NetworkComic): Comic? {
        val relatedDates = input.dates?.let {
            relatedDatesTransformer.transform(it)
        } ?: emptyList()
        val relatedTexts = input.textObjects?.let {
            relatedTextsTransformer.transform(it)
        } ?: emptyList()
        val relatedLinks = input.urls?.let {
            relatedLinksTransformer.transform(it)
        } ?: emptyList()
        val relatedPrices = input.prices?.let {
            relatedPricesTransformer.transform(it)
        } ?: emptyList()
        val creatorsOutput = input.creators?.let {
            roledCreatorTransformer.transform(it)
        } ?: RoledCreatorOutput(emptyMap(), emptyMap())

        val seriesEntityReference = input.series?.let {
            entityReferenceTransformer.transform(it)
        }

        // Sometimes description comes as empty but ISSUE_SOLICIT_TEXT is basically a description.
        // Pick one of these two, then drop ISSUE_SOLICIT_TEXT.
        val description =
            input.description.dropIfEmpty()
                ?: relatedTexts.find { textTypesToCountAsDescription.contains(it.type) }?.text
        val filteredRelatedTexts = relatedTexts.filter { !textTypesToCountAsDescription.contains(it.type) }

        return safeLet(
            input.id,
            input.title,
            input.modified?.let { datesTransformer.transform(it) }
        ) { id, title, modified ->
            Comic(
                id = id,
                displayName = title,
                image = loadableImageTransformer.transform(
                    LoadableImageTransformerInput(
                        networkImage = input.thumbnail,
                        defaultImageType = DefaultImageType.BOOK
                    )
                ),
                navContext = NavigationContext(
                    NavigationHelper.getDetailsRoute(
                        EntityType.COMICS,
                        id,
                        title
                    )
                ),
                pageCount = input.pageCount,
                issueNumber = input.issueNumber,
                lastModified = modified,
                relatedDates = relatedDates,
                relatedTexts = filteredRelatedTexts,
                relatedPrices = relatedPrices,
                relatedLinks = relatedLinks,
                variantDescription = input.variantDescription.dropIfEmpty(),
                description = description,
                upc = input.upc.dropIfEmpty(),
                diamondCode = input.diamondCode.dropIfEmpty(),
                ean = input.ean.dropIfEmpty(),
                issn = input.issn.dropIfEmpty(),
                format = input.format.dropIfEmpty(),
                relatedEventsCount = input.events.getAvailableItems(),
                relatedStoriesCount = input.stories.getAvailableItems(),
                relatedCharactersCount = input.characters.getAvailableItems(),
                relatedCreatorsCount = input.creators.getAvailableItems(),
                relatedSeriesCount = 0,
                relatedComicsCount = 0,
                comicCreatorsByRole = creatorsOutput.primaryCreators,
                coverCreatorsByRole = creatorsOutput.coverCreators,
                seriesEntityReference = seriesEntityReference
            )
        }
    }
}