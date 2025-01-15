package com.wongislandd.portfolio.desktop

enum class WidgetType {
    ABOUT_ME,
    PAINT
}

data class Widget(val type: WidgetType, val title: String)

data class TaskbarWidget(
    val widget: Widget,
    val selected: Boolean = false,
    val minimized: Boolean = false,
    val renderOrder: Int = 0,
)