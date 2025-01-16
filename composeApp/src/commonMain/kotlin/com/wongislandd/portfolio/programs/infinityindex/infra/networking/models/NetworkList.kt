package com.wongislandd.portfolio.programs.infinityindex.infra.networking.models

import kotlinx.serialization.Serializable

@Serializable
data class NetworkList<T>(
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val items: List<T>?
)

fun <T> NetworkList<T>?.getAvailableItems(): Int = this?.available ?: 0