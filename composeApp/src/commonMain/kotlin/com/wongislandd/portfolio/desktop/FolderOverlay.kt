package com.wongislandd.portfolio.desktop

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.wongislandd.nexus.util.noIndicationClickable
import com.wongislandd.portfolio.desktop.data.FolderWidget
import com.wongislandd.portfolio.desktop.vm.LocalDesktopViewModel
import com.wongislandd.portfolio.desktop.vm.CloseFolderEvent
import kotlinx.coroutines.launch

@Composable
fun FolderOverlay(openFolder: FolderWidget, modifier: Modifier = Modifier) {
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
                DesktopWidgetGrid(openFolder.childWidgets)
            }
        }
    }
}