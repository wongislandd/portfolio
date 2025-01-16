package com.wongislandd.portfolio.programs.infinityindex.infra.composables

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val shimmerColors = listOf(
    Color.White.copy(alpha = 0.3f),
    Color.White.copy(alpha = 0.5f),
    Color.White.copy(alpha = 1.0f),
    Color.White.copy(alpha = 0.5f),
    Color.White.copy(alpha = 0.3f),
)

@Composable
fun ShimmerEffect(
    modifier: Modifier = Modifier,
    sizeOfShadowBrush: Int = 700,
    colors: List<Color> = shimmerColors,
    angleOfAxisY: Float = 270f,
    durationMillis: Int = 1500,
    isVertical: Boolean = false,
    content: @Composable BoxScope.() -> Unit
) {
    val transition = rememberInfiniteTransition(label = "")

    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = (durationMillis + sizeOfShadowBrush).toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = durationMillis,
                easing = LinearEasing,
            ),
            repeatMode = RepeatMode.Restart,
        ),
        label = "Shimmer loading animation",
    )

    val brush = if (isVertical) {
        Brush.linearGradient(
            colors = colors,
            start = Offset(x = 0f, y = translateAnimation.value - sizeOfShadowBrush),
            end = Offset(x = 0f, y = translateAnimation.value),
        )
    } else {
        Brush.linearGradient(
            colors = colors,
            start = Offset(x = translateAnimation.value - sizeOfShadowBrush, y = 0.0f),
            end = Offset(x = translateAnimation.value, y = angleOfAxisY),
        )
    }

    Box(
        modifier = modifier
    ) {
        // Dynamic content
        content()

        // Overlay shimmer effect
        Spacer(
            modifier = Modifier
                .matchParentSize()
                .background(brush)
        )
    }
}