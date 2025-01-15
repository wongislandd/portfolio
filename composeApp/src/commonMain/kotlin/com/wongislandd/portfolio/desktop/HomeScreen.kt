package com.wongislandd.portfolio.desktop

import DocumentIcon
import Folder
import GithubIcon
import LinkedInIcon
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Warning
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
import com.wongislandd.nexus.util.noIndicationClickable
import com.wongislandd.portfolio.LocalDesktopViewModel
import com.wongislandd.portfolio.desktop.icons.FaceIcon
import com.wongislandd.portfolio.desktop.icons.IzanIcon
import com.wongislandd.portfolio.desktop.icons.Minimize
import com.wongislandd.portfolio.desktop.icons.Palette
import com.wongislandd.portfolio.programs.chrisinfo.AboutMe
import com.wongislandd.portfolio.programs.drawingboard.DrawingBoardScreen
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
    val appViewModel = LocalDesktopViewModel.current
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
        ProgramWindowBar(taskbarWidget.programWidget)
        ProgramContents(taskbarWidget.programWidget)
    }
}

@Composable
private fun ProgramContents(
    programWidget: ProgramWidget,
    modifier: Modifier = Modifier.fillMaxSize()
) {
    when (programWidget.type) {
        ProgramKey.ABOUT_ME -> {
            AboutMe(modifier)
        }

        ProgramKey.PAINT -> {
            DrawingBoardScreen()
        }
    }
}

@Composable
private fun ProgramWindowBar(programWidget: ProgramWidget) {
    Row(
        modifier = Modifier.fillMaxWidth().background(
            MaterialTheme.colors.primary
        ).padding(start = 16.dp), horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = programWidget.displayName,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier.padding(8.dp)
        )
        ProgramWindowActions(programWidget)
    }
}

@Composable
private fun ProgramWindowActions(programWidget: ProgramWidget, modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()
    val appViewModel = LocalDesktopViewModel.current
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            coroutineScope.launch {
                appViewModel.uiEventBus.sendEvent(WidgetMinimizedEvent(programWidget))
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
                appViewModel.uiEventBus.sendEvent(WidgetClosedEvent(programWidget))
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
    val appViewModel = LocalDesktopViewModel.current
    val desktopScreenState by appViewModel.desktopScreenStateSlice.screenState.collectAsState()
    val openFolder = desktopScreenState.openFolder
    Box(
        modifier = modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colors.background,
                        MaterialTheme.colors.background.copy(alpha = .4f)
                    )
                )
            )
    ) {
        Box(
            modifier = Modifier.fillMaxSize().padding(vertical = 36.dp, horizontal = 16.dp)
        ) {
            DesktopWidgetGrid(desktopScreenState.desktopWidgets)
        }
        AnimatedVisibility(modifier = Modifier.fillMaxSize(), visible = openFolder != null) {
            openFolder?.also {
                FolderOverlay(it)
            }
        }
    }
}

@Composable
private fun FolderOverlay(openFolder: FolderWidget, modifier: Modifier = Modifier) {
    val appViewModel = LocalDesktopViewModel.current
    val coroutineScope = rememberCoroutineScope()
    Box(modifier = modifier.background(Color.White.copy(alpha = .6f)).noIndicationClickable {
        coroutineScope.launch {
            appViewModel.uiEventBus.sendEvent(CloseFolderEvent)
        }
    }) {
        Surface(
            modifier = Modifier.widthIn(400.dp, 700.dp).heightIn(400.dp, 700.dp)
                .align(Alignment.Center)
                .noIndicationClickable(),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(8.dp, MaterialTheme.colors.onSurface)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = openFolder.displayName,
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.onSurface,
                        modifier = Modifier.padding(8.dp)
                    )
                }
                DesktopWidgetGrid(openFolder.programWidgets)
            }
        }
    }
}


@Composable
private fun DesktopWidgetGrid(
    widgets: List<Widget>,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val appViewModel = LocalDesktopViewModel.current
    LazyVerticalGrid(modifier = modifier, columns = GridCells.FixedSize(size = 100.dp)) {
        items(widgets.size) { index ->
            val widget = widgets[index]
            DesktopWidget(widget) {
                coroutineScope.launch {
                    appViewModel.uiEventBus.sendEvent(
                        WidgetClickedEvent(
                            widget
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun Taskbar(modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()
    val appViewModel = LocalDesktopViewModel.current

    val taskbarScreenState by appViewModel.taskbarScreenStateSlice.screenState.collectAsState()
    Row(
        modifier = modifier.fillMaxWidth().height(40.dp)
            .background(color = MaterialTheme.colors.primary)
    ) {
        IconButton(onClick = {}, modifier = Modifier.padding(8.dp)) {
            Icon(IzanIcon, contentDescription = "CW OS", tint = MaterialTheme.colors.onPrimary)
        }
        LazyRow {
            items(taskbarScreenState.activeWidgets.size) { index ->
                val taskbarWidget = taskbarScreenState.activeWidgets[index]
                TaskbarWidget(taskbarWidget) {
                    coroutineScope.launch {
                        appViewModel.uiEventBus.sendEvent(
                            WidgetClickedEvent(
                                taskbarWidget.programWidget
                            )
                        )
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
        ).padding(horizontal = 16.dp).clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        WidgetIcon(taskbarWidget.programWidget.iconKey)
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            taskbarWidget.programWidget.displayName,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onBackground
        )
    }
}

@Composable
private fun WidgetIcon(iconKey: IconKey) {
    val iconToUse = when (iconKey) {
        IconKey.PERSON -> {
            FaceIcon
        }

        IconKey.PALETTE -> {
            Palette
        }

        IconKey.FOLDER -> {
            Folder
        }

        IconKey.LINKEDIN -> {
            LinkedInIcon
        }

        IconKey.DOCUMENT -> {
            DocumentIcon
        }

        IconKey.GITHUB -> {
            GithubIcon
        }

        IconKey.DEFAULT -> {
            Icons.Default.Warning
        }
    }
    Icon(
        iconToUse, contentDescription = "Paint", modifier = Modifier.size(48.dp),
        tint = MaterialTheme.colors.onBackground
    )
}

@Composable
private fun DesktopWidget(widget: Widget, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column(
        modifier = modifier.size(80.dp).clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        WidgetIcon(widget.iconKey)
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            widget.displayName,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.weight(1f)
        )
    }
}