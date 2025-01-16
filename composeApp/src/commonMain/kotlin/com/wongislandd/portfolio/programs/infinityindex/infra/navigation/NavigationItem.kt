enum class NavigationItemType {
    OTHER,
    DETAILS,
    ALL,
    RELATED
}

val ID_ARG = "id"
val ROOT_ENTITY_ARG = "rootEntity"
val ROOT_ENTITY_ID_ARG = "rootEntityId"
val TITLE_ARG = "title"

enum class NavigationItem(
    val displayName: String,
    private val baseRoute: String,
    val type: NavigationItemType = NavigationItemType.OTHER
) {
    Splash("Splash", "splash"),
    Browse("Browse", "browse"),
    Settings("Settings", "settings"),
    RelatedComicListScreen(
        "Related",
        "comics/related",
        NavigationItemType.RELATED
    ),
    RelatedCharactersListScreen(
        "Related",
        "characters/related",
        NavigationItemType.RELATED
    ),
    RelatedCreatorsListScreen(
        "Related",
        "creators/related",
        NavigationItemType.RELATED
    ),
    RelatedSeriesListScreen(
        "Related",
        "series/related",
        NavigationItemType.RELATED
    ),
    RelatedEventsListScreen(
        "Related",
        "events/related",
        NavigationItemType.RELATED
    ),
    RelatedStoriesListScreen(
        "Related",
        "stories/related",
        NavigationItemType.RELATED
    ),
    AllComicListScreen("All Comics", "comics", NavigationItemType.ALL),
    AllCreatorListScreen("All Creators", "creators", NavigationItemType.ALL),
    AllCharacterListScreen("All Characters", "characters", NavigationItemType.ALL),
    AllSeriesListScreen("All Series", "series", NavigationItemType.ALL),
    AllEventListScreen("All Events", "events", NavigationItemType.ALL),
    AllStoriesListScreen("All Stories", "stories", NavigationItemType.ALL),
    ComicDetailsScreen("Comic Details", "comics/details", NavigationItemType.DETAILS),
    CreatorDetailsScreen(
        "Creator Details",
        "creators/details",
        NavigationItemType.DETAILS
    ),
    CharacterDetailsScreen(
        "Character Details",
        "character/details",
        NavigationItemType.DETAILS
    ),
    SeriesDetailsScreen("Series Details", "series/details", NavigationItemType.DETAILS),
    EventDetailsScreen("Event Details", "event/details", NavigationItemType.DETAILS),
    StoryDetailsScreen("Story Details", "story/details", NavigationItemType.DETAILS);

    val route: String
        get() {
            val additionalArgs = getAdditionalArgs()
            val extras = additionalArgs?.let { args ->
                "/" + args.joinToString("/") { "{$it}" }
            } ?: ""
            return this.baseRoute + extras
        }

    fun reconstructRoute(
        args: Map<String, Any?> = emptyMap()
    ): String {
        val baseRoute = this.baseRoute
        val additionalArgs = getAdditionalArgs()
        return buildString {
            append(baseRoute)
            additionalArgs?.forEach { arg ->
                append("/${args[arg]}")
            }
        }
    }

    private fun getAdditionalArgs(): List<String>? {
        return when (this) {
            RelatedComicListScreen,
            RelatedCharactersListScreen,
            RelatedCreatorsListScreen,
            RelatedSeriesListScreen,
            RelatedEventsListScreen,
            RelatedStoriesListScreen -> listOf(
                ROOT_ENTITY_ARG,
                ROOT_ENTITY_ID_ARG,
                TITLE_ARG
            )

            ComicDetailsScreen,
            CreatorDetailsScreen,
            CharacterDetailsScreen,
            SeriesDetailsScreen,
            EventDetailsScreen,
            StoryDetailsScreen -> listOf(ID_ARG, TITLE_ARG)

            AllComicListScreen,
            AllCreatorListScreen,
            AllCharacterListScreen,
            AllSeriesListScreen,
            AllEventListScreen,
            AllStoriesListScreen,
            Browse,
            Settings,
            Splash -> null
        }
    }
}