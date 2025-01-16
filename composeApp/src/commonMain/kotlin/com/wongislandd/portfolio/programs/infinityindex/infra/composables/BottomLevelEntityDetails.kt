package com.wongislandd.portfolio.programs.infinityindex.infra.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityModel

@Composable
fun LastModifiedLabel(entity: EntityModel, modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "${entity.getSingleTypeName()} last modified on ${entity.lastModified}",
            style = MaterialTheme.typography.caption,
            fontWeight = MaterialTheme.typography.caption.fontWeight,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}