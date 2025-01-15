package com.wongislandd.nexus.weblink

import java.awt.Desktop
import java.net.URI

actual class WebLinkRouterImpl actual constructor(context: Any?) : WebLinkRouter {
    override fun openWebLink(url: String) {
        val uri = URI(url)
        if (Desktop.isDesktopSupported()) {
            val desktop = Desktop.getDesktop()
            desktop.browse(uri)
        }
    }
}