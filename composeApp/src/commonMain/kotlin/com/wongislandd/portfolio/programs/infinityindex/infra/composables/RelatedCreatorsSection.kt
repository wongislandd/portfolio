package com.wongislandd.portfolio.programs.infinityindex.infra.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun RelatedCreatorsSection(
    creatorsByRole: Map<String, List<String>>,
    coverCreatorsByRole: Map<String, List<String>>,
    modifier: Modifier = Modifier
) {
    if (creatorsByRole.isNotEmpty() || coverCreatorsByRole.isNotEmpty()) {
        DetailsSection("Creators", modifier = modifier) {
            creatorsByRole.forEach { (role, creators) ->
                InformationSnippet(role, creators.joinToString())
            }
            coverCreatorsByRole.forEach { (role, creators) ->
                InformationSnippet(role, creators.joinToString())
            }
        }
    }
}
