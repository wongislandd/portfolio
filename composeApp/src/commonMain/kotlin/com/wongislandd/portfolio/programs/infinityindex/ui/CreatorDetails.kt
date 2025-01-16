package com.wongislandd.portfolio.programs.infinityindex.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wongislandd.portfolio.programs.infinityindex.infra.EntityDetails
import com.wongislandd.portfolio.programs.infinityindex.infra.composables.MarvelLinks
import com.wongislandd.portfolio.programs.infinityindex.models.local.Creator

@Composable
fun CreatorDetails(creator: Creator, modifier: Modifier = Modifier) {
    EntityDetails(creator, modifier = modifier) {
        MarvelLinks(creator.relatedLinks)
    }
}