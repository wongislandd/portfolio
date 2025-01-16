package com.wongislandd.portfolio.programs.infinityindex.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wongislandd.portfolio.programs.infinityindex.infra.EntityDetails
import com.wongislandd.portfolio.programs.infinityindex.infra.composables.RelatedCreatorsSection
import com.wongislandd.portfolio.programs.infinityindex.models.local.Story
import com.wongislandd.portfolio.programs.infinityindex.infra.composables.SimpleDetailsSection

@Composable
fun StoryDetails(story: Story, modifier: Modifier = Modifier) {
    EntityDetails(story, modifier) {
        SimpleDetailsSection(header = "Description", text = story.description)
        RelatedCreatorsSection(story.creatorsByRole, emptyMap())
    }
}