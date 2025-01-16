package com.wongislandd.portfolio.programs.infinityindex.sortoptions

import com.wongislandd.portfolio.programs.infinityindex.infra.util.SortOption

enum class CreatorsSortOption(
    override val displayName: String,
    override val sortKey: String?,
    override val isDefault: Boolean = false
) : SortOption {
    DEFAULT("Default", null),
    FIRST_NAME_ASC("First Name (A-Z)", "firstName"),
    FIRST_NAME_DEC("First Name (Z-A)", "-firstName"),
    LAST_NAME_ASC("Last Name (A-Z)", "lastName", isDefault = true),
    LAST_NAME_DEC("Last Name (Z-A)", "-lastName"),
    MIDDLE_NAME_ASC("Middle Name (A-Z)", "middleName"),
    MIDDLE_NAME_DEC("Middle Name (Z-A)", "-middleName"),
}