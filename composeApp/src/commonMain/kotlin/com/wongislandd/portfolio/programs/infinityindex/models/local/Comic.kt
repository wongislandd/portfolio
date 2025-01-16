package com.wongislandd.portfolio.programs.infinityindex.models.local

import com.wongislandd.portfolio.programs.infinityindex.models.util.RelatedDate
import com.wongislandd.portfolio.programs.infinityindex.models.util.RelatedLink
import com.wongislandd.portfolio.programs.infinityindex.models.util.RelatedPrice
import com.wongislandd.portfolio.programs.infinityindex.models.util.RelatedText
import com.wongislandd.portfolio.programs.infinityindex.infra.models.LoadableImage
import com.wongislandd.portfolio.programs.infinityindex.infra.models.NavigationContext
import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.EntityReference
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityModel

data class Comic(
    override val id: Int,
    override val displayName: String,
    override val image: LoadableImage,
    override val navContext: NavigationContext,
    val pageCount: Int?,
    val issueNumber: Double?,
    override val lastModified: String,
    val relatedDates: List<RelatedDate>,
    val relatedTexts: List<RelatedText>,
    val relatedPrices: List<RelatedPrice>,
    val relatedLinks: List<RelatedLink>,
    val variantDescription: String?,
    val description: String?,
    val upc: String?,
    val diamondCode: String?,
    val ean: String?,
    val issn: String?,
    val format: String?,
    val comicCreatorsByRole: Map<String, List<String>>,
    val coverCreatorsByRole: Map<String, List<String>>,
    val seriesEntityReference: EntityReference?,
    override val relatedEventsCount: Int,
    override val relatedStoriesCount: Int,
    override val relatedCharactersCount: Int,
    override val relatedCreatorsCount: Int,
    override val relatedComicsCount: Int,
    override val relatedSeriesCount: Int
): EntityModel