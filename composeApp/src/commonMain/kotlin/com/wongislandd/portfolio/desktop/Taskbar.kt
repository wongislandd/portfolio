package com.wongislandd.portfolio.desktop

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wongislandd.portfolio.desktop.data.TaskbarWidget
import com.wongislandd.portfolio.desktop.icons.IzanIcon
import com.wongislandd.portfolio.desktop.vm.LocalDesktopViewModel
import com.wongislandd.portfolio.desktop.vm.ToggleWidgetFinderVisibility
import com.wongislandd.portfolio.desktop.vm.WidgetClickedEvent
import kotlinx.coroutines.launch

@Composable
fun Taskbar(modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()
    val appViewModel = LocalDesktopViewModel.current

    val taskbarScreenState by appViewModel.taskbarScreenStateSlice.screenState.collectAsState()
    Row(
        modifier = modifier.fillMaxWidth().height(40.dp)
            .background(color = MaterialTheme.colors.primary)
    ) {
        IconButton(onClick = {
            coroutineScope.launch {
                appViewModel.uiEventBus.sendEvent(
                    ToggleWidgetFinderVisibility()
                )
            }
        }, modifier = Modifier.padding(8.dp)) {
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
        ).clickable(onClick = onClick).padding(horizontal = 16.dp),
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