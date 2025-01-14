package com.wongislandd.portfolio

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.wongislandd.portfolio.di.initializeKoin

fun main() = application {

    initializeKoin()

    Window(
        onCloseRequest = ::exitApplication,
        title = "portfolio",
    ) {
        PortfolioApp()
    }
}