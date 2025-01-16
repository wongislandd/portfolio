package com.wongislandd.portfolio.programs.infinityindex.infra.util

enum class EntityType(
    val displayName: String,
    val key: String,
    val searchParamType: SearchParamType?
) {
    COMICS(
        "Comics",
        "comics",
        SearchParamType.TITLE_STARTS_WITH
    ),
    CHARACTERS(
        "Characters",
        "characters",
        SearchParamType.NAME_STARTS_WITH
    ),
    CREATORS(
        "Creators",
        "creators",
        SearchParamType.NAME_STARTS_WITH
    ),
    EVENTS(
        "Events",
        "events",
        SearchParamType.NAME_STARTS_WITH
    ),
    SERIES(
        "Series",
        "series",
        SearchParamType.TITLE_STARTS_WITH
    ),
    STORIES(
        "Stories",
        "stories",
        null
    ),
}

enum class SearchParamType(val key: String) {
    TITLE_STARTS_WITH("titleStartsWith"),
    NAME_STARTS_WITH("nameStartsWith"),
}