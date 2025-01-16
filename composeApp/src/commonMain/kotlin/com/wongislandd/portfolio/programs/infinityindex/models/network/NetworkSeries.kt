package com.wongislandd.portfolio.programs.infinityindex.models.network

import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.NetworkImage
import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.NetworkList
import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.NetworkSummary
import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.NetworkUrl
import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.RoledNetworkSummary
import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.TypedNetworkSummary
import kotlinx.serialization.Serializable

@Serializable
data class NetworkSeries(
    val id: Int?,
    val title: String?,
    val description: String?,
    val resourceURI: String?,
    val type: String?,
    val urls: List<NetworkUrl>?,
    val startYear: Int?,
    val endYear: Int?,
    val rating: String?,
    val modified: String?,
    val thumbnail: NetworkImage?,
    val comics: NetworkList<NetworkSummary>?,
    val stories: NetworkList<TypedNetworkSummary>?,
    val events: NetworkList<NetworkSummary>?,
    val characters: NetworkList<NetworkSummary>?,
    val creators: NetworkList<RoledNetworkSummary>?,
    val next: NetworkSummary?,
    val previous: NetworkSummary?
)