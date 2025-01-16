package com.wongislandd.portfolio.programs.infinityindex.infra.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.wongislandd.portfolio.programs.infinityindex.infra.navigation.LocalNavHostController
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityModel
import com.wongislandd.portfolio.programs.infinityindex.infra.util.conditionallyChain

private val entityCardHeight = 285.dp
private val entityCardWidth = 200.dp

@Composable
fun EntityCard(
    entity: EntityModel,
    modifier: Modifier = Modifier
) {
    val navController = LocalNavHostController.current
    BaseEntityCard(
        modifier = modifier
            .conditionallyChain(entity.navContext.allowNavigation, Modifier.clickable {
                navController.navigate(entity.navContext.navRoute)
            })
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            MarvelImage(
                image = entity.image,
                contentScale = ContentScale.Crop,
                tint = MaterialTheme.colors.onPrimary,
                modifier = Modifier.fillMaxSize()
            )
            // Gradient overlay at the bottom
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .height(100.dp)
                    .blur(8.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                MaterialTheme.colors.primary.copy(alpha = 0.4f),
                                MaterialTheme.colors.primary.copy(alpha = 0.6f),
                                MaterialTheme.colors.primary.copy(alpha = 0.7f),
                                MaterialTheme.colors.primary.copy(alpha = 0.9f)
                            ),
                            startY = 0f,
                            endY = 180f
                        )
                    )
            )

            Text(
                text = entity.displayName,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
        }
    }
}

@Composable
fun GhostEntityCard(modifier: Modifier = Modifier) {
    ShimmerEffect(modifier = modifier) {
        BaseEntityCard { }
    }
}

@Composable
fun SeeMoreEntityCard(modifier: Modifier = Modifier) {
    BaseEntityCard(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "See all",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "See More",
                tint = MaterialTheme.colors.onPrimary
            )
        }
    }
}

@Composable
private fun BaseEntityCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 4.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.height(entityCardHeight).width(entityCardWidth)
        ) {
            content()
        }
    }
}