package com.wongislandd.portfolio.desktop

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.wongislandd.nexus.util.noIndicationClickable
import com.wongislandd.portfolio.desktop.data.Widget
import com.wongislandd.portfolio.desktop.vm.DesktopClickedEvent
import com.wongislandd.portfolio.desktop.vm.LocalDesktopViewModel
import com.wongislandd.portfolio.desktop.vm.SearchTermUpdate
import com.wongislandd.portfolio.desktop.vm.WidgetClickedEvent
import kotlinx.coroutines.launch


@Composable
fun WidgetFinder(modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()
    val appViewModel = LocalDesktopViewModel.current
    val widgetFinderScreenState by appViewModel.widgetFinderScreenStateSlice.screenState.collectAsState()
    AnimatedVisibility(
        modifier = modifier.fillMaxSize(),
        visible = widgetFinderScreenState.isVisible
    ) {
        Box(
            modifier = modifier.fillMaxSize().noIndicationClickable {
                coroutineScope.launch {
                    appViewModel.uiEventBus.sendEvent(DesktopClickedEvent)
                }
            }
        ) {
            Surface(
                modifier = Modifier.align(Alignment.BottomStart)
                    .size(height = 500.dp, width = 300.dp)
                    .border(BorderStroke(1.dp, MaterialTheme.colors.onSurface))
                    .noIndicationClickable { }
                    .background(MaterialTheme.colors.surface).padding(32.dp),
            ) {
                Column {
                    Text(
                        "Widget Finder",
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.onSurface,
                        textAlign = TextAlign.Start,
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    Column(modifier = Modifier.width(250.dp)) {
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth().weight(1f),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            if (widgetFinderScreenState.widgetList.isEmpty()) {
                                item {
                                    Text(
                                        "No widgets found",
                                        style = MaterialTheme.typography.body1,
                                        color = MaterialTheme.colors.onBackground
                                    )
                                }
                            }
                            items(widgetFinderScreenState.widgetList.size) { index ->
                                val widget = widgetFinderScreenState.widgetList[index]
                                WidgetFinderItem(widget) {
                                    coroutineScope.launch {
                                        appViewModel.uiEventBus.sendEvent(
                                            WidgetClickedEvent(widget)
                                        )
                                    }
                                }
                            }
                        }
                        WidgetFinderSearchBar(widgetFinderScreenState.searchTerm)
                    }
                }
            }
        }
    }
}

@Composable
private fun WidgetFinderSearchBar(searchTerm: String) {
    val coroutineScope = rememberCoroutineScope()
    val appViewModel = LocalDesktopViewModel.current
    TextField(searchTerm, onValueChange = {
        coroutineScope.launch {
            appViewModel.uiEventBus.sendEvent(SearchTermUpdate(it))
        }
    }, singleLine = true, placeholder = { Text("Search widgets") })
}

@Composable
private fun WidgetFinderItem(widget: Widget, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Row(
        modifier = modifier.fillMaxWidth().clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        WidgetIcon(widget.iconKey)
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            widget.displayName,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onBackground,
            overflow = TextOverflow.Ellipsis
        )
    }
}