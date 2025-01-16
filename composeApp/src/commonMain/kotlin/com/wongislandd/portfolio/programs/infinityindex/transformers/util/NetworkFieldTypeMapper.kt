package com.wongislandd.portfolio.programs.infinityindex.transformers.util

import co.touchlab.kermit.Logger
import com.wongislandd.portfolio.programs.infinityindex.models.util.DateType
import com.wongislandd.portfolio.programs.infinityindex.models.util.LinkType
import com.wongislandd.portfolio.programs.infinityindex.models.util.PriceType
import com.wongislandd.portfolio.programs.infinityindex.models.util.TextType

class NetworkFieldTypeMapper {

    private val dateTypeMap = mapOf(
        "onsaleDate" to DateType.ONSALE_DATE,
        "focDate" to DateType.FOC_DATE,
        "unlimitedDate" to DateType.UNLIMITED_DATE,
        "digitalPurchaseDate" to DateType.DIGITAL_PURCHASE_DATE
    )

    private val textTypeMap = mapOf(
        // Issue solicit seems to always be the description. Take that in favor of this.
        "issue_solicit_text" to TextType.ISSUE_SOLICIT_TEXT,
        "preview_text" to TextType.PREVIEW_TEXT,
        "issue_preview_text" to TextType.ISSUE_PREVIEW_TEXT
    )

    private val priceTypeMap = mapOf(
        "printPrice" to PriceType.PRINT_PRICE,
        "digitalPurchasePrice" to PriceType.DIGITAL_PURCHASE_PRICE
    )

    private val linkTypeMap = mapOf(
        "detail" to LinkType.DETAILS,
        "purchase" to LinkType.PURCHASE,
        "reader" to LinkType.READER,
        "inAppLink" to LinkType.IN_APP_LINK,
        "wiki" to LinkType.WIKI,
        "comiclink" to LinkType.COMIC_LINK
    )

    fun mapDateType(dateType: String): DateType {
        return dateTypeMap.getOrElse(dateType) {
            logDrop("Date Type: $dateType")
            DateType.UNKNOWN
        }
    }

    fun mapTextType(textType: String): TextType {
        return textTypeMap.getOrElse(textType) {
            logDrop("Text Type: $textType")
            TextType.UNKNOWN
        }
    }

    fun mapPriceType(priceType: String): PriceType {
        return priceTypeMap.getOrElse(priceType) {
            logDrop("Price Type: $priceType")
            PriceType.UNKNOWN
        }
    }

    fun mapLinkType(linkType: String): LinkType {
        return linkTypeMap.getOrElse(linkType) {
            logDrop("Link Type: $linkType")
            LinkType.UNKNOWN
        }
    }
}

private fun logDrop(droppedType: String) {
    Logger.i(tag = "Network Field Mapper", null) { "Dropped type: $droppedType" }
}

fun String.capitalizeEachWord(): String {
    return this.split(" ")
        .joinToString(" ") { it.lowercase().replaceFirstChar { char -> char.uppercaseChar() } }
}