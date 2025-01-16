package com.wongislandd.portfolio.programs.infinityindex.infra.util

interface SortOption {
    val displayName: String
    val sortKey: String?
    val isDefault: Boolean
}