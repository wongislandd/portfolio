package com.wongislandd.portfolio.programs.infinityindex.infra.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.wongislandd.portfolio.programs.infinityindex.infra.models.DefaultImageType

@Composable
fun UnknownImage(
    imageType: DefaultImageType,
    tint: Color = MaterialTheme.colors.onPrimary,
    modifier: Modifier = Modifier
) {
    val placeholderIcon = when (imageType) {
        DefaultImageType.PERSON -> Icons.Default.Person
        DefaultImageType.PLACE -> Icons.Default.Place
        DefaultImageType.BOOK -> MenuBook
        DefaultImageType.THING -> QuestionMark
    }
    Box(
        modifier = modifier
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 48.dp)
    ) {
        Icon(
            imageVector = placeholderIcon,
            contentDescription = "Unknown image",
            tint = tint,
            modifier = Modifier.size(100.dp).align(Alignment.Center)
        )
    }
}