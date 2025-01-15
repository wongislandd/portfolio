package com.wongislandd.portfolio.programs.drawingboard

import com.wongislandd.nexus.util.Resource

data class DrawingBoardScreenState(
    val canvasState: Resource<CanvasState> = Resource.Loading(),
    val isColorPickerShown: Boolean = false,
    val isThicknessSelectorShown: Boolean = false,
)