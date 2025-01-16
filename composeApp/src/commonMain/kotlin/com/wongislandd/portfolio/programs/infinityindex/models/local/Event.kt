package com.wongislandd.portfolio.programs.infinityindex.models.local

import com.wongislandd.portfolio.programs.infinityindex.infra.models.LoadableImage
import com.wongislandd.portfolio.programs.infinityindex.infra.models.NavigationContext
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityModel

data class Event(
    override val id: Int,
    override val displayName: String,
    override val image: LoadableImage,
    override val navContext: NavigationContext,
    val description: String?,
    val start: String?,
    val end: String?,
    override val relatedComicsCount: Int,
    override val relatedStoriesCount: Int,
    override val relatedCharactersCount: Int,
    override val relatedCreatorsCount: Int,
    override val relatedSeriesCount: Int,
    override val relatedEventsCount: Int,
    override val lastModified: String
): EntityModel