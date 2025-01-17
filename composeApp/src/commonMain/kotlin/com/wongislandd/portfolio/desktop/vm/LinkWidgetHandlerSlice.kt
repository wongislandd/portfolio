package com.wongislandd.portfolio.desktop.vm

import com.wongislandd.nexus.weblink.WebLinkRouter
import com.wongislandd.portfolio.desktop.data.LinkWidget

class LinkWidgetHandlerSlice(
    private val webLinkRouter: WebLinkRouter
) : WidgetClickHandlerSlice<LinkWidget>() {
    override fun handleWidgetClicked(clickedWidget: LinkWidget) {
        webLinkRouter.openWebLink(clickedWidget.url)
    }
}