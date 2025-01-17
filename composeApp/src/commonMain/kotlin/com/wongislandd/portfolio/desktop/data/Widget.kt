package com.wongislandd.portfolio.desktop.data

interface Widget {
    val displayName: String
    val iconKey: IconKey
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

