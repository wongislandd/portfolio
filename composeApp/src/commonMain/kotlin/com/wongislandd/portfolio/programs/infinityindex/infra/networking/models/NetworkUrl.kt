package com.wongislandd.portfolio.programs.infinityindex.infra.networking.models

import kotlinx.serialization.Serializable

@Serializable
data class NetworkUrl(
    val type: String?,
    val url: String?
)