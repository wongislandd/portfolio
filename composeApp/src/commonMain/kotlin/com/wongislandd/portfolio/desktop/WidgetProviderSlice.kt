package com.wongislandd.portfolio.desktop

import com.wongislandd.nexus.viewmodel.ViewModelSlice
import kotlinx.coroutines.launch

class WidgetProviderSlice : ViewModelSlice() {
    private val programWidgets = listOf(
        ProgramWidget("About Me", IconKey.PERSON, ProgramKey.ABOUT_ME),
        FolderWidget(
            "Links",
            IconKey.FOLDER,
            listOf(
                LinkWidget("GitHub", IconKey.GITHUB, "https://github.com/wongislandd"),
                LinkWidget(
                    "LinkedIn",
                    IconKey.LINKEDIN,
                    "https://www.linkedin.com/in/christopherwong99/"
                ),
                LinkWidget(
                    "Resume",
                    IconKey.DOCUMENT,
                    "https://drive.google.com/file/d/1sI1TLofgTk0WmziGjX9jX2k3zu0CaRnk/view?usp=sharing"
                )
            )
        ),
        ProgramWidget("Paint", IconKey.PALETTE, ProgramKey.PAINT),
        ProgramWidget("Infinity Index", IconKey.DEFAULT, ProgramKey.INFINITY_INDEX),
    )

    override fun afterInit() {
        super.afterInit()
        sliceScope.launch {
            backChannelEvents.sendEvent(DesktopWidgetsUpdate(programWidgets))
        }
    }
}