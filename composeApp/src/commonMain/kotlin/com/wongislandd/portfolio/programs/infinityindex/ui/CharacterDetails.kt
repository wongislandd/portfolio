package com.wongislandd.portfolio.programs.infinityindex.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wongislandd.portfolio.programs.infinityindex.infra.EntityDetails
import com.wongislandd.portfolio.programs.infinityindex.infra.composables.MarvelLinks
import com.wongislandd.portfolio.programs.infinityindex.infra.composables.SimpleDetailsSection
import com.wongislandd.portfolio.programs.infinityindex.models.local.Character

@Composable
fun CharacterDetails(character: Character, modifier: Modifier = Modifier) {
    EntityDetails(character, modifier = modifier) {
        SimpleDetailsSection("Description", character.description)
        MarvelLinks(character.relatedLinks)
    }
}