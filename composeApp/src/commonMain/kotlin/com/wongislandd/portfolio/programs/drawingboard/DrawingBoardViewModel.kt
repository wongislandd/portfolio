package com.wongislandd.portfolio.programs.drawingboard

import com.wongislandd.nexus.events.BackChannelEvent
import com.wongislandd.nexus.events.EventBus
import com.wongislandd.nexus.events.UiEvent
import com.wongislandd.nexus.viewmodel.SliceableViewModel

class DrawingBoardViewModel(
    val drawingBoardScreenStateSlice: DrawingBoardScreenStateSlice,
    private val canvas: Canvas,
    canvasPathSlice: CanvasPathSlice,
    canvasSettingsSlice: BrushControllerSlice,
    uiEventBus: EventBus<UiEvent>,
    backChannelEventBus: EventBus<BackChannelEvent>
) : SliceableViewModel(uiEventBus, backChannelEventBus) {

    init {
        listOf(
            canvasSettingsSlice,
            canvasPathSlice,
            drawingBoardScreenStateSlice,
        ).forEach { slice ->
            slice.provideCanvas(canvas)
            registerSlices(slice)
        }
    }

    fun registerCanvasTitle(title: String) {
        canvas.updateCanvasTitle(title)
    }
}