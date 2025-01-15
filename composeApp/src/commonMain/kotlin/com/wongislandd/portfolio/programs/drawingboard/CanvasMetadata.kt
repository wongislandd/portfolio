package com.wongislandd.portfolio.programs.drawingboard

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class CanvasMetadata(
    val date: Instant = Clock.System.now(),
    val title: String = "Work of Art",
)