package com.wongislandd.portfolio.desktop

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.wongislandd.nexus.util.conditionallyChain
import com.wongislandd.portfolio.desktop.data.ProgramKey
import com.wongislandd.portfolio.desktop.data.ProgramWidget
import com.wongislandd.portfolio.desktop.data.TaskbarWidget
import com.wongislandd.portfolio.desktop.vm.LocalDesktopViewModel
import com.wongislandd.portfolio.desktop.icons.Minimize
import com.wongislandd.portfolio.desktop.vm.WidgetClosedEvent
import com.wongislandd.portfolio.desktop.vm.WidgetMinimizedEvent
import com.wongislandd.portfolio.programs.chrisinfo.AboutMe
import com.wongislandd.portfolio.programs.inspector.Inspector
import kotlinx.coroutines.launch

@Composable
fun ProgramWindow(taskbarWidget: TaskbarWidget, modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier.fillMaxSize()
                .conditionallyChain(
                    taskbarWidget.minimized,
                    Modifier.alpha(0f)
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProgramWindowBar(taskbarWidget.programWidget)
            ProgramContents(taskbarWidget.programWidget)
        }
        // Block any interaction with the window if it's minimized
        if (taskbarWidget.minimized) {
            Box(modifier = Modifier.fillMaxSize().clickable(enabled = false) { })
        }
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
        ProgramKey.INSPECTOR -> {
            Inspector(modifier)
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
