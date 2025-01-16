package com.wongislandd.portfolio.programs.infinityindex.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wongislandd.portfolio.programs.infinityindex.infra.EntityDetails
import com.wongislandd.portfolio.programs.infinityindex.infra.composables.RelatedCreatorsSection
import com.wongislandd.portfolio.programs.infinityindex.infra.composables.DetailsSection
import com.wongislandd.portfolio.programs.infinityindex.infra.composables.InformationSnippet
import com.wongislandd.portfolio.programs.infinityindex.infra.composables.MarvelLinks
import com.wongislandd.portfolio.programs.infinityindex.infra.composables.SimpleDetailsSection
import com.wongislandd.portfolio.programs.infinityindex.models.local.Comic

@Composable
fun ComicDetails(comic: Comic, modifier: Modifier = Modifier) {
    EntityDetails(comic, modifier = modifier) {
        SimpleDetailsSection("Description", comic.description)
        RelatedCreatorsSection(comic.comicCreatorsByRole, comic.coverCreatorsByRole)
        DetailsSection("Relevant Dates") {
            comic.relatedDates.forEach { relatedDates ->
                InformationSnippet(relatedDates.type.displayName, relatedDates.date)
            }
        }
        DetailsSection("Additional Information") {
            comic.issueNumber?.let {
                InformationSnippet("Issue Number", it.toString())
            }
            comic.relatedTexts.forEach { relatedText ->
                InformationSnippet(relatedText.type.displayName, relatedText.text)
            }
            comic.variantDescription?.let {
                InformationSnippet("Variant Description", it)
            }
            comic.pageCount?.let { pageCount ->
                InformationSnippet("Page Count", pageCount.toString())
            }
            comic.format?.let { format ->
                InformationSnippet("Format", format)
            }
            comic.upc?.let {
                InformationSnippet("UPC", it)
            }
            comic.diamondCode?.let {
                InformationSnippet("Diamond Code", it)
            }
            comic.ean?.let {
                InformationSnippet("EAN", it)
            }
            comic.issn?.let {
                InformationSnippet("ISSN", it)
            }
            comic.relatedPrices.forEach { relatedPrice ->
                InformationSnippet(relatedPrice.type.displayName, relatedPrice.price)
            }
        }
        MarvelLinks(comic.relatedLinks)
    }
}