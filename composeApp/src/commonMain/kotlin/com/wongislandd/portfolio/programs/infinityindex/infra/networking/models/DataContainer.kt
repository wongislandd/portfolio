package com.wongislandd.portfolio.programs.infinityindex.infra.networking.models

// Simplified concrete DataContainer class
data class DataContainer<T>(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<T>
)