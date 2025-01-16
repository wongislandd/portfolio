package com.wongislandd.portfolio.programs.infinityindex.models.network

import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.NetworkImage
import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.NetworkList
import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.NetworkSummary
import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.RoledNetworkSummary
import kotlinx.serialization.Serializable

@Serializable
data class NetworkStory(
    val id: Int?,
    val title: String?,
    val description: String?,
    val resourceURI: String?,
    val type: String?,
    val modified: String?,
    val thumbnail: NetworkImage?,
    val comics: NetworkList<NetworkSummary>?,
    val series: NetworkList<NetworkSummary>?,
    val events: NetworkList<NetworkSummary>?,
    val characters: NetworkList<NetworkSummary>?,
    val creators: NetworkList<RoledNetworkSummary>?,
    val originalIssue: NetworkSummary?
)