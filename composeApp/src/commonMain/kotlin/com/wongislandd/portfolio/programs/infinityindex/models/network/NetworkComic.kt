package com.wongislandd.portfolio.programs.infinityindex.models.network

import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.NetworkImage
import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.NetworkList
import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.NetworkSummary
import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.NetworkUrl
import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.RoledNetworkSummary
import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.TypedNetworkSummary
import kotlinx.serialization.Serializable

@Serializable
data class NetworkComic(
    val id: Int?,
    val digitalId: Int?,
    val title: String?,
    val issueNumber: Double?,
    val variantDescription: String?,
    val description: String?,
    val modified: String?,
    val isbn: String?,
    val upc: String?,
    val diamondCode: String?,
    val ean: String?,
    val issn: String?,
    val format: String?,
    val pageCount: Int?,
    val textObjects: List<NetworkTextObject>?,
    val resourceURI: String?,
    val urls: List<NetworkUrl>?,
    val series: NetworkSummary?,
    val variants: List<NetworkSummary>?,
    val collections: List<NetworkSummary>?,
    val collectedIssues: List<NetworkSummary>?,
    val dates: List<NetworkComicDate>?,
    val prices: List<NetworkComicPrice>?,
    val thumbnail: NetworkImage?,
    val images: List<NetworkImage>?,
    val creators: NetworkList<RoledNetworkSummary>?,
    val characters: NetworkList<NetworkSummary>?,
    val stories: NetworkList<TypedNetworkSummary>?,
    val events: NetworkList<NetworkSummary>?
)

@Serializable
data class NetworkTextObject(
    val type: String?,
    val language: String?,
    val text: String?
)

@Serializable
data class NetworkComicDate(
    val type: String?,
    val date: String?
)

@Serializable
data class NetworkComicPrice(
    val type: String?,
    val price: Float?
)




