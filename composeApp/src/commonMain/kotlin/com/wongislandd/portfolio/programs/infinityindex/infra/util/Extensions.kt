package com.wongislandd.portfolio.programs.infinityindex.infra.util

import androidx.compose.ui.Modifier

fun Modifier.conditionallyChain(condition: Boolean, modifier: Modifier): Modifier {
    return if (condition) this.then(modifier) else this
}

fun String?.dropIfEmpty() = this?.takeIf { it.isNotBlank() }