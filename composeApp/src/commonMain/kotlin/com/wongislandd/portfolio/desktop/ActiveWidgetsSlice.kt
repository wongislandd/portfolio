package com.wongislandd.portfolio.desktop

import com.wongislandd.nexus.events.UiEvent
import com.wongislandd.nexus.viewmodel.ViewModelSlice
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class WidgetClickedEvent(
    val widget: Widget
) : UiEvent

class ActiveWidgetsSlice : ViewModelSlice() {

    private val _activeWidgets: MutableStateFlow<List<SelectableWidget>> =
        MutableStateFlow(listOf())

    override fun handleUiEvent(event: UiEvent) {
        super.handleUiEvent(event)
        when (event) {
            is WidgetClickedEvent -> {
                handleDesktopWidgetClicked(event.widget)
            }
        }
    }

    private fun handleDesktopWidgetClicked(clickedWidget: Widget) {
        sliceScope.launch {
            _activeWidgets.update { currentActiveWidgets ->
                var result = currentActiveWidgets
                if (!result.contains(clickedWidget)) {
                    result = result + SelectableWidget(clickedWidget)
                }
                result.map { activeWidget ->
                    activeWidget.copy(selected = activeWidget.widget == clickedWidget)
                }
            }
            backChannelEvents.sendEvent(ActiveWidgetsUpdate(_activeWidgets.value))
        }
    }
}

private fun List<SelectableWidget>.contains(widget: Widget): Boolean {
    return this.any { it.widget == widget }
}