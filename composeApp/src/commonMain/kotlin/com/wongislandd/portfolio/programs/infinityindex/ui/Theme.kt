package com.wongislandd.portfolio.programs.infinityindex.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Primary and secondary colors
val primaryColor = Color(0xFFB22222) // Dark Red (Iron Man-inspired)
val secondaryColor = Color(0xFFFFC107) // Marvel Gold (Iron Man's arc reactor)

// Background color (darkish gray for dark theme, white for light theme)
val backgroundColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF121212) else Color(0xFFFAFAFA) // Dark gray for dark mode, off-white for light mode

// Text color (soft off-white/light gray for dark background, black for light theme)
val textColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFFFFFFF) else Color(0xFF333333) // Light gray for dark theme, dark gray for light theme

// Surface color (off-white for light theme, dark gray for dark theme)
val surfaceColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF1E1E1E) else Color(0xFFF5F5F5) // Dark gray for dark mode, off-white for light mode

val MarvelColors
    @Composable
    get() = if (isSystemInDarkTheme()) {
        darkColors(
            primary = primaryColor,
            onPrimary = Color.White, // White text on dark red
            secondary = secondaryColor, // Marvel Gold for secondary color
            background = surfaceColor, // Dark background
            surface = surfaceColor, // Dark surface
            onSurface = textColor, // Light text on surface
            onBackground = textColor // Light text on background
        )
    } else {
        lightColors(
            primary = primaryColor,
            onPrimary = Color.White, // White text on dark red
            secondary = secondaryColor, // Marvel Gold for secondary color
            background = surfaceColor, // Light background
            surface = surfaceColor, // Off-white surface
            onSurface = textColor, // Dark text on surface
            onBackground = textColor // Dark text on background
        )
    }

@Composable
fun MarvelTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = MarvelColors,
        content = content
    )
}