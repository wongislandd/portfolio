package com.wongislandd.portfolio.desktop.vm

import androidx.compose.runtime.staticCompositionLocalOf

val LocalDesktopViewModel = staticCompositionLocalOf<DesktopViewModel> {
    error("No DesktopViewModel provided")
}