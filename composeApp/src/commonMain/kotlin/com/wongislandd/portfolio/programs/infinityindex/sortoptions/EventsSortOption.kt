package com.wongislandd.portfolio.programs.infinityindex.sortoptions

import com.wongislandd.portfolio.programs.infinityindex.infra.util.SortOption

enum class EventsSortOption(
    override val displayName: String,
    override val sortKey: String?,
    override val isDefault: Boolean = false
) : SortOption {
    DEFAULT(displayName = "Default", sortKey = null, isDefault = true),
    NAME_DESC(displayName = "Name (dec)", sortKey = "-name"),
    NAME_ASC(displayName = "Name (asc)", sortKey = "name"),
    MODIFIED_DESC(displayName = "Last Modified (dec)", sortKey = "-modified"),
    MODIFIED_ASC(displayName = "Last Modified (asc)", sortKey = "modified"),
}