package com.wongislandd.portfolio.programs.inspector

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wongislandd.nexus.events.Event
import com.wongislandd.portfolio.desktop.vm.InspectorEventsLogClearedEvent
import com.wongislandd.portfolio.desktop.vm.LocalDesktopViewModel
import kotlinx.coroutines.launch

@Composable
fun Inspector(modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()
    val appViewModel = LocalDesktopViewModel.current
    val events by appViewModel.inspectorScreenStateSlice.events.collectAsState()
    EventsList(events = events, onClear = {
        coroutineScope.launch {
            appViewModel.uiEventBus.sendEvent(
                InspectorEventsLogClearedEvent
            )
        }
    }, modifier = modifier)
}

@Composable
private fun EventsList(events: List<Event>, onClear: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize().background(MaterialTheme.colors.surface)
            .padding(horizontal = 16.dp),
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(
                "Here's all the communication going on behind the scenes for this app (latest on top)",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.size(16.dp))
            Button(onClick = onClear) {
                Text("Clear")
            }
        }
        LazyColumn(
            modifier = modifier.weight(1f),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(events.size) { index ->
                val event = events[index]
                EventEntry(event)
                Spacer(modifier = Modifier.height(8.dp))
                if (index != events.lastIndex) {
                    Divider()
                }
            }
        }
    }
}

@Composable
private fun EventEntry(event: Event) {
    Text(event.toString(), color = MaterialTheme.colors.onSurface)
}