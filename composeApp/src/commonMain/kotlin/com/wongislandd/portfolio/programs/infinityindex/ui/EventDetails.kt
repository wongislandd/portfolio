package com.wongislandd.portfolio.programs.infinityindex.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wongislandd.portfolio.programs.infinityindex.infra.EntityDetails
import com.wongislandd.portfolio.programs.infinityindex.infra.composables.InformationSnippet
import com.wongislandd.portfolio.programs.infinityindex.models.local.Event
import com.wongislandd.portfolio.programs.infinityindex.infra.composables.DetailsSection
import com.wongislandd.portfolio.programs.infinityindex.infra.composables.SimpleDetailsSection
import com.wongislandd.portfolio.programs.infinityindex.infra.util.safeLet

@Composable
fun EventDetails(event: Event, modifier: Modifier = Modifier) {
    EntityDetails(event, modifier = modifier) {
        SimpleDetailsSection("Description", event.description)
        DetailsSection("Additional Information") {
            safeLet(event.start, event.end) { start, end ->
                InformationSnippet("Active", "$start - $end")
            }
        }
    }
}