package com.wongislandd.portfolio.desktop.vm

import com.wongislandd.nexus.events.BackChannelEvent
import com.wongislandd.nexus.events.UiEvent
import com.wongislandd.nexus.viewmodel.ViewModelSlice
import com.wongislandd.portfolio.desktop.data.Widget
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class ToggleWidgetFinderVisibility(
    val isVisible: Boolean? = null
) : UiEvent

data class WidgetFinderScreenState(
    val isVisible: Boolean = false,
    val widgetList: List<Widget> = listOf(),
    val searchTerm: String = ""
)

data class SearchTermUpdate(
    val searchTerm: String
) : UiEvent

class WidgetFinderScreenStateSlice : ViewModelSlice() {

    private var allAvailableWidgets: List<Widget> = listOf()

    private val _screenState = MutableStateFlow(WidgetFinderScreenState())
    val screenState: StateFlow<WidgetFinderScreenState>
        get() = _screenState

    override fun handleUiEvent(event: UiEvent) {
        super.handleUiEvent(event)
        when (event) {
            is DesktopClickedEvent -> {
                _screenState.update {
                    it.copy(isVisible = false)
                }
            }
            is ToggleWidgetFinderVisibility -> {
                _screenState.update {
                    it.copy(isVisible = event.isVisible ?: !it.isVisible)
                }
            }

            is SearchTermUpdate -> {
                applySearch(event.searchTerm)
            }
        }
    }

    override fun handleBackChannelEvent(event: BackChannelEvent) {
        super.handleBackChannelEvent(event)
        when (event) {
            is AvailableWidgetsUpdate -> {
                allAvailableWidgets =
                    event.availableWidgets.sortedBy { widget -> widget.displayName }
                _screenState.update {
                    it.copy(widgetList = allAvailableWidgets)
                }
            }
        }
    }

    private fun applySearch(searchTerm: String) {
        if (searchTerm.isEmpty()) {
            _screenState.update {
                it.copy(widgetList = allAvailableWidgets,
                    searchTerm = "")
            }
        } else {
            _screenState.update {
                it.copy(
                    widgetList = allAvailableWidgets.filter { widget ->
                        widget.displayName.contains(
                            searchTerm,
                            ignoreCase = true
                        )
                    },
                    searchTerm = searchTerm
                )
            }
        }
    }
}