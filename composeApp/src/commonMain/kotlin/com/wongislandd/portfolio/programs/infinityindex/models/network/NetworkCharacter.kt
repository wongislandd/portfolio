package com.wongislandd.portfolio.programs.infinityindex.models.network

import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.NetworkList
import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.NetworkSummary
import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.TypedNetworkSummary
import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.NetworkImage
import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.NetworkUrl
import kotlinx.serialization.Serializable

@Serializable
data class NetworkCharacter(
    val id: Int?,
    val name: String?,
    val description: String?,
    val modified: String?,
    val resourceURI: String?,
    val urls: List<NetworkUrl>?,
    val thumbnail: NetworkImage?,
    val comics: NetworkList<NetworkSummary>?,
    val stories: NetworkList<TypedNetworkSummary>?,
    val events: NetworkList<NetworkSummary>?,
    val series: NetworkList<NetworkSummary>?
)