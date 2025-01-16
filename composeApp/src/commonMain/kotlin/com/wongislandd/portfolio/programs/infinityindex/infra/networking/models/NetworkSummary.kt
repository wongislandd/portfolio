package com.wongislandd.portfolio.programs.infinityindex.infra.networking.models

import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import kotlinx.serialization.Serializable

data class EntityReference(
    val entityType: EntityType,
    val entityId: Int,
    val name: String
)

@Serializable
data class NetworkSummary(
    val resourceURI: String?,
    val name: String?,
)

@Serializable
data class RoledNetworkSummary(
    val resourceURI: String?,
    val name: String?,
    val role: String?
)

@Serializable
data class TypedNetworkSummary(
    val resourceURI: String?,
    val name: String?,
    val type: String?
)