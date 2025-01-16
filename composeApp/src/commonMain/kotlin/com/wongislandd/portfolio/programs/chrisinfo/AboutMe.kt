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
            "Hi! My name is Chris and I'm a software engineer. This is sort of a portfolio, but also just a fun playground to build things in. " +
                    "Feel free to look around, although there isn't much yet. This is the type of thing to forever be a work in progress as there is always more to learn! " +
                    "Fun fact, this was all built using Kotlin Multiplatform and compose to build out the UI. That means that I get to write everything once and it works across web, Android, iOS, and even a native desktop app!",
            color = MaterialTheme.colors.onSurface
        )
    }
}