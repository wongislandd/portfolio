package com.wongislandd.portfolio.programs.infinityindex.infra.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.wongislandd.portfolio.programs.infinityindex.infra.util.DisplayableEntity
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityModel
import com.wongislandd.portfolio.programs.infinityindex.models.local.Character
import com.wongislandd.portfolio.programs.infinityindex.models.local.Comic
import com.wongislandd.portfolio.programs.infinityindex.models.local.Creator
import com.wongislandd.portfolio.programs.infinityindex.models.local.Event
import com.wongislandd.portfolio.programs.infinityindex.models.local.Series
import com.wongislandd.portfolio.programs.infinityindex.models.local.Story

@Composable
fun TopLevelEntityDetails(entity: EntityModel, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(modifier = Modifier.widthIn(max=350.dp), shape = RoundedCornerShape(8.dp)) {
            MarvelImage(
                image = entity.image,
                tint = MaterialTheme.colors.onSurface,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Text(
            text = entity.displayName,
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        EntityTypePlate(entity)
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
private fun EntityTypePlate(displayableEntity: DisplayableEntity, modifier: Modifier = Modifier) {
    val text = displayableEntity.getSingleTypeName()
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(50)) // Makes the edges rounded
            .background(MaterialTheme.colors.secondary) // Background color of the pill
            .padding(horizontal = 16.dp, vertical = 8.dp) // Padding for the text
    ) {
        Text(
            text = text,
            color = MaterialTheme.colors.onSecondary,
            style = MaterialTheme.typography.caption
        )
    }
}

fun DisplayableEntity.getSingleTypeName(): String {
    return when (this) {
        is Character -> "Character"
        is Event -> "Event"
        is Story -> "Story"
        is Comic -> "Comic"
        is Series -> "Series"
        is Creator -> "Creator"
        else -> "Unknown"
    }
}