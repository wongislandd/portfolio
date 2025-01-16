package com.wongislandd.portfolio.programs.infinityindex.sortoptions

import com.wongislandd.portfolio.programs.infinityindex.infra.util.SortOption

enum class SeriesSortOption(
    override val displayName: String,
    override val sortKey: String?,
    override val isDefault: Boolean = false
) : SortOption {
    DEFAULT(displayName = "Default", sortKey = null),
    TITLE_DESC(displayName = "Title (dec)", sortKey = "-title"),
    TITLE_ASC(displayName = "Title (asc)", sortKey = "title"),
    START_YEAR_DEC(displayName = "Start Year (dec)", sortKey = "-startYear", isDefault = true),
    START_YEAR_ASC(displayName = "Start Year (asc)", sortKey = "startYear"),
    MODIFIED_DESC(displayName = "Last Modified (dec)", sortKey = "-modified"),
    MODIFIED_ASC(displayName = "Last Modified (asc)", sortKey = "modified"),
}