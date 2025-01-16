package com.wongislandd.portfolio.programs.infinityindex.infra.util

import com.wongislandd.portfolio.programs.infinityindex.infra.models.LoadableImage
import com.wongislandd.portfolio.programs.infinityindex.infra.models.NavigationContext

interface DisplayableEntity {
    val id: Int
    val displayName: String
    val image: LoadableImage
    val navContext: NavigationContext
}