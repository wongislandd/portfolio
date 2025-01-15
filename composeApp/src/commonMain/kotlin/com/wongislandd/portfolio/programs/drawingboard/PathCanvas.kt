package com.wongislandd.portfolio.programs.drawingboard

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import com.wongislandd.nexus.events.EventBus
import com.wongislandd.nexus.events.UiEvent
import com.wongislandd.nexus.events.sendEvent
import kotlin.math.abs

@Composable
fun PathsCanvas(
    pathState: PathState,
    uiEventBus: EventBus<UiEvent>,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val sendEvent: (UiEvent) -> Unit = { event ->
        coroutineScope.sendEvent(
            uiEventBus,
            event
        )
    }
    Canvas(modifier = modifier
        .clipToBounds()
        .fillMaxSize()
        .background(Color.White)
        // Account for single taps
        .pointerInput(true) {
            detectTapGestures(
                onTap = { offset ->
                    sendEvent(DrawingAction.OnTap(offset))
                }
            )
        }
        // Account for drags
        .pointerInput(true) {
            detectDragGestures(
                onDragStart = {
                    sendEvent(DrawingAction.OnNewPathStart)
                },
                onDrag = { change, _ ->
                    sendEvent(DrawingAction.OnDraw(change.position))
                },
                onDragEnd = {
                    sendEvent(DrawingAction.OnNewPathEnd)
                },
                onDragCancel = {
                    sendEvent(DrawingAction.OnNewPathEnd)
                }
            )
        }

    ) {
        drawPathState(pathState)
    }
}

fun DrawScope.drawPathState(pathState: PathState) {
    // Draw previous paths
    pathState.paths.fastForEach { pathData ->
        drawPath(
            path = pathData.offsets,
            color = pathData.color,
            thickness = pathData.thickness
        )
    }
    // Draw current path
    pathState.currentPath?.let { currentPathData ->
        drawPath(
            path = currentPathData.offsets,
            color = currentPathData.color,
            thickness = currentPathData.thickness
        )
    }
}

fun DrawScope.drawPath(
    path: List<Offset>,
    color: Color,
    thickness: Dp = 10.dp
) {
    val smoothedPath = Path().apply {
        if (path.isNotEmpty()) {
            if (path.size == 1) {
                // Draw a dot if there's only one offset in the path, no need to link lines
                drawCircle(color, radius = thickness.toPx() / 2, center = path.first())
                return@apply
            }
            moveTo(path.first().x, path.first().y)
            val smoothness = 2
            for (i in 1 until path.lastIndex) {
                val from = path[i - 1]
                val to = path[i]
                val dx = abs(from.x - to.x)
                val dy = abs(from.y - to.y)
                if (dx >= smoothness || dy >= smoothness) {
                    quadraticTo(
                        x1 = (from.x + to.x) / 2f,
                        y1 = (from.y + to.y) / 2f,
                        x2 = to.x,
                        y2 = to.y
                    )
                }
            }
        }
    }
    drawPath(
        path = smoothedPath,
        color = color,
        style = Stroke(
            width = thickness.toPx(),
            cap = StrokeCap.Round,
            join = StrokeJoin.Round
        )
    )
}