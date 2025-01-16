package com.wongislandd.portfolio.programs.infinityindex.infra.networking.models

import kotlinx.serialization.Serializable

@Serializable
data class NetworkImage(
    val path: String?,
    val extension: String?
)