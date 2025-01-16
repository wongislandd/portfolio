package com.wongislandd.portfolio.programs.infinityindex.infra.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SimpleDetailsSection(
    header: String,
    text: String?,
    modifier: Modifier = Modifier
) {
    text?.also {
        Column(
            modifier = modifier
                .fillMaxWidth()
        ) {
            DetailsSection(header) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface
                )
            }
        }
    }
}

@Composable
fun DetailsSection(
    text: String,
    modifier: Modifier = Modifier,
    additionalTitlePadding: Dp? = null,
    content: @Composable () -> Unit = {}
) {
    val titleModifier = additionalTitlePadding?.let { Modifier.padding(horizontal = it) } ?: Modifier
    Column(modifier = modifier) {
        Text(
            text = text,
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.primary,
            modifier = titleModifier
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            content()
        }
    }
}