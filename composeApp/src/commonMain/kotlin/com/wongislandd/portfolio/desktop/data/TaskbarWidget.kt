package com.wongislandd.portfolio.desktop.data

data class TaskbarWidget(
    val programWidget: ProgramWidget,
    val selected: Boolean = false,
    val minimized: Boolean = false,
    val renderOrder: Int = 0,
)