package com.wongislandd.portfolio.programs.infinityindex.infra

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

object DateHelper {

    fun getCurrentYear(): Int {
        val currentDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        return currentDate.year
    }

    fun isYearInTheFuture(year: Int): Boolean {
        return year > getCurrentYear()
    }
}