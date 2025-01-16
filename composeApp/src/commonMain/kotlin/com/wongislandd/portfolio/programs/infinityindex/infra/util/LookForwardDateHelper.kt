package com.wongislandd.portfolio.programs.infinityindex.infra.util

import kotlinx.datetime.Clock
import kotlin.time.Duration.Companion.days

object LookForwardDateHelper {
    fun getLookForwardDateRange(lookForwardDays: Int): String {
        val currentDate = Clock.System.now()

        // Look forward period
        val futureDate = currentDate.plus(lookForwardDays.days)

        // Format the date as YYYY-MM-DD
        val startDate = "1900-01-01"
        val formattedDate = futureDate.toString()
        return "$startDate,$formattedDate"
    }
}