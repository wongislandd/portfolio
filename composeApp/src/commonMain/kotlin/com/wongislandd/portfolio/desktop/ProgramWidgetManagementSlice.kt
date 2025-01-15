package com.wongislandd.portfolio.desktop

import com.wongislandd.nexus.events.UiEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class WidgetClickedEvent(
    val widget: Widget,
) : UiEvent

data class WidgetClosedEvent(
    val widget: ProgramWidget
) : UiEvent

data class WidgetMinimizedEvent(
    val widget: ProgramWidget
) : UiEvent

class ProgramWidgetManagementSlice : WidgetClickHandlerSlice<ProgramWidget>() {

    // the order is the order in which they are rendered, selected one should always be the top
    private val _activeWidgets: MutableStateFlow<List<TaskbarWidget>> =
        MutableStateFlow(listOf())

    override fun handleUiEvent(event: UiEvent) {
        super.handleUiEvent(event)
        when (event) {
            is WidgetMinimizedEvent -> {
                handleWidgetMinimized(event.widget)
            }

            is WidgetClosedEvent -> {
                handleWidgetClosed(event.widget)
            }
        }
    }

    private fun handleWidgetClosed(programWidget: ProgramWidget) {
        sliceScope.launch {
            _activeWidgets.update { currentActiveWidgets ->
                currentActiveWidgets.filter { it.programWidget != programWidget }
            }
            backChannelEvents.sendEvent(ActiveWidgetsUpdate(_activeWidgets.value))
        }
    }

    private fun handleWidgetMinimized(minimizedProgramWidget: ProgramWidget) {
        sliceScope.launch {
            _activeWidgets.update { currentActiveWidgets ->
                currentActiveWidgets.map { activeWidget ->
                    activeWidget.copy(
                        selected = false,
                        minimized = if (minimizedProgramWidget == activeWidget.programWidget) true else activeWidget.minimized,
                    )
                }
            }
            backChannelEvents.sendEvent(ActiveWidgetsUpdate(_activeWidgets.value))
        }
    }

    override fun handleWidgetClicked(clickedWidget: ProgramWidget) {
        sliceScope.launch {
            _activeWidgets.update { currentActiveWidgets ->
                var result = currentActiveWidgets
                if (!result.contains(clickedWidget)) {
                    result = result + TaskbarWidget(clickedWidget)
                }
                result.mapIndexed { index, activeWidget ->
                    val isSelected = activeWidget.programWidget == clickedWidget
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

private fun List<TaskbarWidget>.contains(programWidget: ProgramWidget): Boolean {
    return this.any { it.programWidget == programWidget }
}