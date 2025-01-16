package com.wongislandd.portfolio.programs.infinityindex.infra.paging

data class PaginationContextWrapper<T>(
    val items: List<T>,
    val start: Int,
    val count: Int,
    val total: Long
)