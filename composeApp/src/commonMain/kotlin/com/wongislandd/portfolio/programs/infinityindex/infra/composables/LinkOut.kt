package com.wongislandd.portfolio.programs.infinityindex.infra.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import com.wongislandd.portfolio.programs.infinityindex.models.util.RelatedLink

@Composable
private fun LinkOut(link: RelatedLink, modifier: Modifier = Modifier) {
    val uriHandler = LocalUriHandler.current
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            onClick = {
                uriHandler.openUri(link.url)
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.secondary,
                contentColor = MaterialTheme.colors.onSecondary
            )
        ) {
            Text(link.type.displayName)
        }
    }
}

@Composable
fun MarvelLinks(links: List<RelatedLink>, modifier: Modifier = Modifier) {
    DetailsSection("Marvel Provided Links", modifier = modifier) {
        links.forEach { link ->
            LinkOut(link)
        }
    }
}