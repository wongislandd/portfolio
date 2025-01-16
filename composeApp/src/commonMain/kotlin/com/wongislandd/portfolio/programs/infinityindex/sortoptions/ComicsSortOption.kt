package com.wongislandd.portfolio.programs.infinityindex.sortoptions

import com.wongislandd.portfolio.programs.infinityindex.infra.util.SortOption

enum class ComicsSortOption(
    override val displayName: String,
    override val sortKey: String?,
    override val isDefault: Boolean = false
) : SortOption {
    DEFAULT(displayName = "Default", sortKey = null),
    FOC_DATE_DESC(displayName = "FOC Date (dec)", sortKey = "-focDate"),
    FOC_DATE_ASC(displayName = "FOC Date (asc)", sortKey = "focDate"),
    TITLE_DESC(displayName = "Title (dec)", sortKey = "-title"),
    TITLE_ASC(displayName = "Title (asc)", sortKey = "title"),
    MODIFIED_DESC(displayName = "Last Modified (dec)", sortKey = "-modified"),
    MODIFIED_ASC(displayName = "Last Modified (asc)", sortKey = "modified"),
    ONSALE_DATE_DESC(displayName = "On Sale Date (dec)", sortKey = "-onsaleDate", isDefault = true),
    ONSALE_DATE_ASC(displayName = "On Sale Date (asc)", sortKey = "onsaleDate"),
    ISSUE_NUMBER_DESC(displayName = "Issue Number (dec)", sortKey = "-issueNumber"),
    ISSUE_NUMBER_ASC(displayName = "Issue Number (asc)", sortKey = "issueNumber")
}