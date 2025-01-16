package com.wongislandd.portfolio.programs.chrisinfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AboutMe(modifier: Modifier = Modifier) {
    Column(modifier = modifier.background(MaterialTheme.colors.surface).padding(16.dp)) {
        Text(
            "Hi! My name is Chris and this is sort of a portfolio, but also just a fun playground to build things in. " +
                    "Feel free to look around, although there isn't much yet.",
            color = MaterialTheme.colors.onSurface
        )
    }
}