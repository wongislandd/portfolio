package com.wongislandd.portfolio.desktop

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.wongislandd.portfolio.desktop.data.Widget
import com.wongislandd.portfolio.desktop.vm.LocalDesktopViewModel
import com.wongislandd.portfolio.desktop.vm.WidgetClickedEvent
import kotlinx.coroutines.launch

@Composable
fun DesktopWidgetGrid(
    widgets: List<Widget>,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val appViewModel = LocalDesktopViewModel.current
    LazyVerticalGrid(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        columns = GridCells.FixedSize(size = 100.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
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
private fun DesktopWidget(widget: Widget, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Box(
        modifier = modifier.width(100.dp).height(125.dp).clickable(onClick = onClick).padding(8.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            WidgetIcon(widget.iconKey)
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                widget.displayName,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }
    }

}