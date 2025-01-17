package com.wongislandd.portfolio.desktop

enum class ProgramKey {
    ABOUT_ME,
}

enum class IconKey {
    PERSON,
    FOLDER,
    PALETTE,
    LINKEDIN,
    GITHUB,
    DOCUMENT,
    GAME,
    COMIC,
    LINK,
    DEFAULT,
}

data class ProgramWidget(
    override val displayName: String,
    override val iconKey: IconKey,
    val type: ProgramKey
) : Widget

data class LinkWidget(
    override val displayName: String,
    override val iconKey: IconKey,
    val url: String
) : Widget

data class FolderWidget(
    override val displayName: String,
    override val iconKey: IconKey = IconKey.FOLDER,
    val childWidgets: List<Widget>
) : Widget

interface Widget {
    val displayName: String
    val iconKey: IconKey
}

data class TaskbarWidget(
    val programWidget: ProgramWidget,
    val selected: Boolean = false,
    val minimized: Boolean = false,
    val renderOrder: Int = 0,
)