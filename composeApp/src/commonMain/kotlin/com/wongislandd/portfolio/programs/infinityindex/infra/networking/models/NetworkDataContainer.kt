package com.wongislandd.portfolio.programs.infinityindex.infra.networking.models

@kotlinx.serialization.Serializable
data class NetworkDataContainer<T>(
    val offset: Int?,
    val limit: Int?,
    val total: Int?,
    val count: Int?,
    val results: List<T>?
)