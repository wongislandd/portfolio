package com.wongislandd.portfolio.desktop

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.wongislandd.portfolio.LocalAppViewModel
import com.wongislandd.portfolio.programs.chrisinfo.ChrisInfo
import com.wongislandd.portfolio.programs.demo.Demo
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        PrimaryContents(modifier = Modifier.weight(1f))
        Taskbar()
    }
}

@Composable
private fun PrimaryContents(modifier: Modifier = Modifier) {
    val appViewModel = LocalAppViewModel.current
    val taskbarScreenState by appViewModel.taskbarScreenStateSlice.screenState.collectAsState()
    val activeWidgets = taskbarScreenState.activeWidgets
    Box(modifier = modifier.fillMaxSize()) {
        Desktop(modifier = Modifier.fillMaxSize())
        for (activeWidget in activeWidgets) {
            if (!activeWidget.minimized) {
                ProgramWindow(activeWidget, modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Composable
private fun ProgramWindow(selectableWidget: SelectableWidget, modifier: Modifier = Modifier) {
    val appViewModel = LocalAppViewModel.current
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = modifier.zIndex(selectableWidget.renderOrder.toFloat()).fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().background(
                MaterialTheme.colors.primary
            ), horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = selectableWidget.widget.title,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.padding(8.dp)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    coroutineScope.launch {
                        appViewModel.uiEventBus.sendEvent(WidgetMinimizedEvent(selectableWidget.widget))
                    }
                }) {
                    Icon(
                        Icons.Default.Build,
                        contentDescription = "Minimize",
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
                IconButton(onClick = {
                    coroutineScope.launch {
                        appViewModel.uiEventBus.sendEvent(WidgetClosedEvent(selectableWidget.widget))
                    }
                }) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "Close",
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
            }
        }
        when (selectableWidget.widget.type) {
            WidgetType.SELF_INFO -> {
                ChrisInfo(modifier = Modifier.fillMaxSize())
            }

            WidgetType.DEMO -> {
                Demo(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
private fun Desktop(modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()
    val appViewModel = LocalAppViewModel.current
    val desktopScreenState by appViewModel.desktopScreenStateSlice.screenState.collectAsState()
    Box(
        modifier = modifier
            .fillMaxWidth().background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colors.background,
                        MaterialTheme.colors.background.copy(alpha = .4f)
                    )
                )
            ).padding(32.dp)
    ) {
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(desktopScreenState.availableWidgets.size) { index ->
                val widget = desktopScreenState.availableWidgets[index]
                ClickableWidget(widget) {
                    coroutineScope.launch {
                        appViewModel.uiEventBus.sendEvent(WidgetClickedEvent(widget))
                    }
                }
            }
        }
    }
}

@Composable
private fun Taskbar(modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()
    val appViewModel = LocalAppViewModel.current

    val taskbarScreenState by appViewModel.taskbarScreenStateSlice.screenState.collectAsState()
    Row(
        modifier = modifier.fillMaxWidth().height(40.dp)
            .background(color = MaterialTheme.colors.primary)
    ) {
        IconButton(onClick = {}, modifier = Modifier.padding(8.dp)) {
            Icon(IzanIcon, contentDescription = "CW OS", tint = MaterialTheme.colors.onPrimary)
        }
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(taskbarScreenState.activeWidgets.size) { index ->
                val activeWidget = taskbarScreenState.activeWidgets[index]
                ActiveWidget(activeWidget) {
                    coroutineScope.launch {
                        appViewModel.uiEventBus.sendEvent(WidgetClickedEvent(activeWidget.widget))
                    }
                }
            }
        }
    }
}

@Composable
private fun ActiveWidget(
    selectableWidget: SelectableWidget,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxHeight().background(
            color = if (selectableWidget.selected) MaterialTheme.colors.secondary else
                MaterialTheme.colors.primary
        ).padding(8.dp).clickable(onClick = onClick),
    ) {
        WidgetIcon(selectableWidget.widget.type)
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            selectableWidget.widget.title,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onBackground
        )
    }
}

@Composable
private fun WidgetIcon(widgetType: WidgetType) {
    when (widgetType) {
        WidgetType.SELF_INFO -> {
            Box(modifier = Modifier.size(30.dp).background(color = Color.Magenta))
        }

        WidgetType.DEMO -> {
            Box(modifier = Modifier.size(30.dp).background(color = Color.Cyan))
        }
    }
}

@Composable
private fun ClickableWidget(widget: Widget, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column(
        modifier = modifier.size(80.dp).clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        WidgetIcon(widget.type)
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            widget.title,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onBackground
        )
    }
}