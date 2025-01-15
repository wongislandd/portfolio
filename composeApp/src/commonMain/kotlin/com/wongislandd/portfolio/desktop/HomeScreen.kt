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
private fun ProgramWindow(taskbarWidget: TaskbarWidget, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.zIndex(taskbarWidget.renderOrder.toFloat()).fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProgramWindowBar(taskbarWidget.widget)
        ProgramContents(taskbarWidget.widget)
    }
}

@Composable
private fun ProgramContents(widget: Widget, modifier: Modifier = Modifier.fillMaxSize()) {
    when (widget.type) {
        WidgetType.SELF_INFO -> {
            ChrisInfo(modifier)
        }

        WidgetType.DEMO -> {
            Demo(modifier)
        }
    }
}

@Composable
private fun ProgramWindowBar(widget: Widget) {
    Row(
        modifier = Modifier.fillMaxWidth().background(
            MaterialTheme.colors.primary
        ), horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = widget.title,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier.padding(8.dp)
        )
        ProgramWindowActions(widget)
    }
}

@Composable
private fun ProgramWindowActions(widget: Widget, modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()
    val appViewModel = LocalAppViewModel.current
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            coroutineScope.launch {
                appViewModel.uiEventBus.sendEvent(WidgetMinimizedEvent(widget))
            }
        }) {
            Icon(
                Minimize,
                contentDescription = "Minimize",
                tint = MaterialTheme.colors.onPrimary
            )
        }
        IconButton(onClick = {
            coroutineScope.launch {
                appViewModel.uiEventBus.sendEvent(WidgetClosedEvent(widget))
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


@Composable
private fun Desktop(modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()
    val appViewModel = LocalAppViewModel.current
    val desktopScreenState by appViewModel.desktopScreenStateSlice.screenState.collectAsState()
    Box(
        modifier = modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colors.background,
                        MaterialTheme.colors.background.copy(alpha = .4f)
                    )
                )
            ).padding(32.dp)
    ) {
        LazyVerticalGrid(columns = GridCells.FixedSize(size = 100.dp)) {
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
                val taskbarWidget = taskbarScreenState.activeWidgets[index]
                TaskbarWidget(taskbarWidget) {
                    coroutineScope.launch {
                        appViewModel.uiEventBus.sendEvent(WidgetClickedEvent(taskbarWidget.widget))
                    }
                }
            }
        }
    }
}

@Composable
private fun TaskbarWidget(
    taskbarWidget: TaskbarWidget,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxHeight().background(
            color = if (taskbarWidget.selected) MaterialTheme.colors.secondary else
                MaterialTheme.colors.primary
        ).padding(8.dp).clickable(onClick = onClick),
    ) {
        WidgetIcon(taskbarWidget.widget.type)
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            taskbarWidget.widget.title,
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