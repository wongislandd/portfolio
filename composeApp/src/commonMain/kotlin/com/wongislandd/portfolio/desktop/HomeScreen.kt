package com.wongislandd.portfolio.desktop


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.wongislandd.nexus.util.noIndicationClickable
import com.wongislandd.portfolio.desktop.data.Widget
import com.wongislandd.portfolio.desktop.vm.DesktopClickedEvent
import com.wongislandd.portfolio.desktop.vm.LocalDesktopViewModel
import com.wongislandd.portfolio.desktop.vm.SearchTermUpdate
import com.wongislandd.portfolio.desktop.vm.WidgetClickedEvent
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
        Desktop(modifier = Modifier.fillMaxSize().zIndex(0f))
        for (activeWidget in activeWidgets) {
            val zIndex = if (activeWidget.minimized) -1f else activeWidget.renderOrder.toFloat()
            ProgramWindow(activeWidget, modifier = Modifier.align(Alignment.Center).zIndex(zIndex))
        }
        // This will always be on top of the stack
        WidgetFinder(modifier = Modifier.zIndex((activeWidgets.size + 1).toFloat()))
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
