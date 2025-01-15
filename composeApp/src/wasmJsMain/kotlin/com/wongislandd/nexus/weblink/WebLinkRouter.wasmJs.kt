package com.wongislandd.nexus.weblink

import kotlinx.browser.window

actual class WebLinkRouterImpl actual constructor(context: Any?) : WebLinkRouter {
    override fun openWebLink(url: String) {
        window.open(url, "_blank")
    }
}