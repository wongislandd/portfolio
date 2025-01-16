package com.wongislandd.portfolio.programs.infinityindex.infra.composables

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin
import com.wongislandd.portfolio.programs.infinityindex.infra.models.LoadableImage

@Composable
fun MarvelImage(
    image: LoadableImage,
    contentScale: ContentScale = ContentScale.Inside,
    tint: Color,
    modifier: Modifier
) {
    image.imageUrl?.let { imageUrl ->
        CoilImage(
            modifier = modifier,
            imageModel = { imageUrl },
            imageOptions = ImageOptions(
                contentScale = contentScale,
                alignment = Alignment.Center
            ),
            component = rememberImageComponent {
                +ShimmerPlugin()
            },
            failure = {
                Text("Could not load image.")
            }
        )
    } ?: UnknownImage(imageType = image.defaultEntity, tint = tint, modifier = modifier)
}