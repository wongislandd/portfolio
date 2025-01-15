package com.wongislandd.portfolio.desktop

enum class WidgetType {
    SELF_INFO,
    DEMO
}

data class Widget(val type: WidgetType, val title: String)

data class TaskbarWidget(
    val widget: Widget,
    val selected: Boolean = false,
    val minimized: Boolean = false,
    val renderOrder: Int = 0,
)