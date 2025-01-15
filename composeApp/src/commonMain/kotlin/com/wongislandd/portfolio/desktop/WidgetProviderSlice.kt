package com.wongislandd.portfolio.desktop

import com.wongislandd.nexus.viewmodel.ViewModelSlice
import kotlinx.coroutines.launch

class WidgetProviderSlice : ViewModelSlice() {
    private val programWidgets = listOf(
        ProgramWidget("About Me", IconKey.PERSON, ProgramKey.ABOUT_ME),
        ProgramWidget("Paint", IconKey.PALETTE, ProgramKey.PAINT),
        FolderWidget(
            "Links",
            IconKey.FOLDER,
            listOf(
                LinkWidget("GitHub", IconKey.DEFAULT, ""),
                LinkWidget("LinkedIn", IconKey.DEFAULT, ""),
                LinkWidget("Resume", IconKey.DEFAULT, "")
            )
        )
    )

    override fun afterInit() {
        super.afterInit()
        sliceScope.launch {
            backChannelEvents.sendEvent(DesktopWidgetsUpdate(programWidgets))
        }
    }
}