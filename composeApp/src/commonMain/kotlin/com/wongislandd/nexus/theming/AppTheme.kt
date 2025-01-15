package com.wongislandd.nexus.theming

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Primary and secondary colors
val primaryColor = Color(0xFF3F51B5) // Muted Indigo
val secondaryColor = Color(0xFF00C853) // Vibrant Green for accent

// Error color
val errorColor = Color(0xFFE57373) // Soft Red for errors

// Background and text colors
val backgroundColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF121212) else Color(0xFFFFFFFF) // Dark gray for dark theme, white for light

val textColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFE0E0E0) else Color(0xFF212121) // Light gray for dark theme, dark gray for light

// Surface colors for light and dark themes
val lightSurfaceColor = Color(0xFFFFFFFF) // White for light theme
val darkSurfaceColor = Color(0xFF121212) // Dark gray for dark theme

val surfaceColor
    @Composable
    get() = if (isSystemInDarkTheme()) darkSurfaceColor else lightSurfaceColor

val ThemeColors
    @Composable
    get() = if (isSystemInDarkTheme()) {
        darkColors(
            primary = primaryColor,
            onPrimary = Color.White,
            secondary = secondaryColor,
            onSecondary = Color.Black,
            background = backgroundColor,
            surface = surfaceColor,
            onSurface = textColor,
            onBackground = textColor,
            error = errorColor,
            onError = Color.White
        )
    } else {
        lightColors(
            primary = primaryColor,
            onPrimary = Color.White,
            secondary = secondaryColor,
            onSecondary = Color.Black,
            background = backgroundColor,
            surface = surfaceColor,
            onSurface = textColor,
            onBackground = textColor,
            error = errorColor,
            onError = Color.White
        )
    }

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = ThemeColors,
        content = content
    )
}