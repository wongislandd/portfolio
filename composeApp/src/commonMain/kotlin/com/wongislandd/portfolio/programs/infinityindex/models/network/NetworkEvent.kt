package com.wongislandd.portfolio.programs.infinityindex.models.network

import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.NetworkList
import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.NetworkSummary
import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.TypedNetworkSummary
import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.NetworkImage
import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.NetworkUrl
import kotlinx.serialization.Serializable

@Serializable
data class NetworkEvent(
    val id: Int?,
    val title: String?,
    val description: String?,
    val resourceURI: String?,
    val urls: List<NetworkUrl>?,
    val modified: String?,
    val start: String?,
    val end: String?,
    val thumbnail: NetworkImage?,
    val comics: NetworkList<NetworkSummary>?,
    val stories: NetworkList<TypedNetworkSummary>?,
    val series: NetworkList<NetworkSummary>?,
    val events: NetworkList<NetworkSummary>?,
    val characters: NetworkList<NetworkSummary>?,
    val creators: NetworkList<NetworkSummary>?,
    val next: NetworkSummary?,
    val previous: NetworkSummary?
)