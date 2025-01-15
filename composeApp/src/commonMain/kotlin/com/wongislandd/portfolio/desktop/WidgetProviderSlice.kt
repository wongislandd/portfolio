package com.wongislandd.portfolio.desktop

import com.wongislandd.nexus.viewmodel.ViewModelSlice
import kotlinx.coroutines.launch

class WidgetProviderSlice: ViewModelSlice() {
    private val widgets = listOf(
        Widget(WidgetType.SELF_INFO, "Info"),
        Widget(WidgetType.DEMO, "Demo")
    )

    override fun afterInit() {
        super.afterInit()
        sliceScope.launch {
            backChannelEvents.sendEvent(AvailableWidgetsUpdate(widgets))
        }
    }
}