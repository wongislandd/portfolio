package com.wongislandd.portfolio.desktop.vm

import com.wongislandd.nexus.viewmodel.ViewModelSlice
import com.wongislandd.portfolio.desktop.data.FolderWidget
import com.wongislandd.portfolio.desktop.data.IconKey
import com.wongislandd.portfolio.desktop.data.LinkWidget
import com.wongislandd.portfolio.desktop.data.ProgramKey
import com.wongislandd.portfolio.desktop.data.ProgramWidget
import kotlinx.coroutines.launch

class WidgetProviderSlice : ViewModelSlice() {
    private val programWidgets = listOf(
        ProgramWidget("About Me", IconKey.PERSON, ProgramKey.ABOUT_ME),
        LinkWidget("Github", IconKey.LINK, "https://github.com/wongislandd"),
        LinkWidget(
            "LinkedIn",
            IconKey.LINK,
            "https://www.linkedin.com/in/christopherwong99/"
        ),
        LinkWidget(
            "Resume",
            IconKey.LINK,
            "https://drive.google.com/file/d/1sI1TLofgTk0WmziGjX9jX2k3zu0CaRnk/view?usp=sharing"
        ),
        ProgramWidget("Inspector", IconKey.ENGINEER, ProgramKey.INSPECTOR),
        LinkWidget("Daily Doodle", IconKey.PALETTE, "https://wongislandd.github.io/daily-doodle"),
        LinkWidget("Infinity Index", IconKey.COMIC, "https://wongislandd.github.io/infinityindex"),
        LinkWidget("Wordlink", IconKey.GAME, "https://wongislandd.github.io/wordlink"),
        FolderWidget(
            displayName = "College Projects",
            childWidgets = listOf(
                LinkWidget(
                    "Fitt's Tile Game",
                    IconKey.GAME,
                    "https://wongislandd.github.io/fitts-tile-game"
                ),
                LinkWidget("Sheep Hunt", IconKey.GAME, "https://wongislandd.github.io/SheepHunt")
            ),
        )
    )


    override fun afterInit() {
        super.afterInit()
        sliceScope.launch {
            backChannelEvents.sendEvent(AvailableWidgetsUpdate(programWidgets))
        }
    }
}