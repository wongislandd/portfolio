package com.wongislandd.portfolio.desktop

import com.wongislandd.nexus.events.UiEvent
import com.wongislandd.nexus.viewmodel.ViewModelSlice
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class WidgetClickedEvent(
    val widget: Widget
) : UiEvent

data class WidgetClosedEvent(
    val widget: Widget
) : UiEvent

data class WidgetMinimizedEvent(
    val widget: Widget
) : UiEvent

class WidgetManagementSlice : ViewModelSlice() {

    // the order is the order in which they are rendered, selected one should always be the top
    private val _activeWidgets: MutableStateFlow<List<TaskbarWidget>> =
        MutableStateFlow(listOf())

    override fun handleUiEvent(event: UiEvent) {
        super.handleUiEvent(event)
        when (event) {
            is WidgetClickedEvent -> {
                handleWidgetClicked(event.widget)
            }

            is WidgetMinimizedEvent -> {
                handleWidgetMinimized(event.widget)
            }

            is WidgetClosedEvent -> {
                handleWidgetClosed(event.widget)
            }
        }
    }

    private fun handleWidgetClosed(widget: Widget) {
        sliceScope.launch {
            _activeWidgets.update { currentActiveWidgets ->
                currentActiveWidgets.filter { it.widget != widget }
            }
            backChannelEvents.sendEvent(ActiveWidgetsUpdate(_activeWidgets.value))
        }
    }

    private fun handleWidgetMinimized(minimizedWidget: Widget) {
        sliceScope.launch {
            _activeWidgets.update { currentActiveWidgets ->
                currentActiveWidgets.map { activeWidget ->
                    activeWidget.copy(
                        selected = false,
                        minimized = if (minimizedWidget == activeWidget.widget) true else activeWidget.minimized,
                    )
                }
            }
            backChannelEvents.sendEvent(ActiveWidgetsUpdate(_activeWidgets.value))
        }
    }

    private fun handleWidgetClicked(clickedWidget: Widget) {
        sliceScope.launch {
            _activeWidgets.update { currentActiveWidgets ->
                var result = currentActiveWidgets
                if (!result.contains(clickedWidget)) {
                    result = result + TaskbarWidget(clickedWidget)
                }
                result.mapIndexed { index, activeWidget ->
                    val isSelected = activeWidget.widget == clickedWidget
                    activeWidget.copy(
                        selected = isSelected,
                        minimized = if (isSelected) false else activeWidget.minimized,
                        renderOrder = if (isSelected) result.size else index
                    )
                }
            }
            backChannelEvents.sendEvent(ActiveWidgetsUpdate(_activeWidgets.value))
        }
    }
}

private fun List<TaskbarWidget>.contains(widget: Widget): Boolean {
    return this.any { it.widget == widget }
}