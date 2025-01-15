package com.wongislandd.portfolio

import android.app.Application
import org.koin.core.component.KoinComponent

class PortfolioApplication : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
    }
}