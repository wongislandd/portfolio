package com.wongislandd.portfolio.programs.infinityindex.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wongislandd.portfolio.programs.infinityindex.infra.DateHelper
import com.wongislandd.portfolio.programs.infinityindex.infra.EntityDetails
import com.wongislandd.portfolio.programs.infinityindex.infra.composables.DetailsSection
import com.wongislandd.portfolio.programs.infinityindex.infra.composables.InformationSnippet
import com.wongislandd.portfolio.programs.infinityindex.infra.composables.RelatedCreatorsSection
import com.wongislandd.portfolio.programs.infinityindex.infra.composables.SimpleDetailsSection
import com.wongislandd.portfolio.programs.infinityindex.infra.util.safeLet
import com.wongislandd.portfolio.programs.infinityindex.models.local.Series

@Composable
fun SeriesDetails(series: Series, modifier: Modifier = Modifier) {
    EntityDetails(series, modifier = modifier) {
        SimpleDetailsSection("Description", series.description)
        DetailsSection("Additional Information") {
            series.rating?.also { rating ->
                InformationSnippet("Rating", rating)
            }
            safeLet(series.startYear, series.endYear) { startYear, endYear ->
                val endYearOrPresent =
                    if (DateHelper.isYearInTheFuture(endYear)) "Present" else endYear
                InformationSnippet("Active", "$startYear - $endYearOrPresent")
            }
            series.type?.also { type ->
                InformationSnippet("Type", type)
            }
        }
        RelatedCreatorsSection(series.creatorsByRole, emptyMap())
    }
}