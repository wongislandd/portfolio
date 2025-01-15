package com.wongislandd.portfolio

import androidx.compose.runtime.staticCompositionLocalOf

val LocalDesktopViewModel = staticCompositionLocalOf<DesktopViewModel> {
    error("No DesktopViewModel provided")
}