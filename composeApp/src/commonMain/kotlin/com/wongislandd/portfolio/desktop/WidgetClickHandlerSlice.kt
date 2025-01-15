package com.wongislandd.portfolio.desktop

import com.wongislandd.nexus.events.UiEvent
import com.wongislandd.nexus.viewmodel.ViewModelSlice

abstract class WidgetClickHandlerSlice<T : Widget> : ViewModelSlice() {

    override fun handleUiEvent(event: UiEvent) {
        super.handleUiEvent(event)
        when (event) {
            is WidgetClickedEvent -> {
                try {
                    val widget = event.widget as T
                    if (shouldHandleWidgetClick(widget)) {
                        handleWidgetClicked(widget)
                    }
                } catch (e: Exception) {
                    // ignore if unable to cast it, need to find a better solution
                }
            }
        }
    }

    // By default, handle anything that matches the type
    open fun shouldHandleWidgetClick(widget: T): Boolean = true

    abstract fun handleWidgetClicked(clickedWidget: T)
}