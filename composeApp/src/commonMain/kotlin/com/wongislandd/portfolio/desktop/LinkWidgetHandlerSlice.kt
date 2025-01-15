package com.wongislandd.portfolio.desktop

import com.wongislandd.nexus.weblink.WebLinkRouter

class LinkWidgetHandlerSlice(
    private val webLinkRouter: WebLinkRouter
) : WidgetClickHandlerSlice<LinkWidget>() {
    override fun handleWidgetClicked(clickedWidget: LinkWidget) {
        webLinkRouter.openWebLink(clickedWidget.url)
    }
}