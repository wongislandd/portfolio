package com.wongislandd.portfolio.programs.infinityindex.infra.transformers

import com.wongislandd.portfolio.programs.infinityindex.infra.models.DefaultImageType
import com.wongislandd.portfolio.programs.infinityindex.infra.models.LoadableImage
import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.NetworkImage
import com.wongislandd.portfolio.programs.infinityindex.infra.util.Transformer

data class LoadableImageTransformerInput(
    val networkImage: NetworkImage?,
    val defaultImageType: DefaultImageType
)

/**
 * Extracts URL from network image
 */
class LoadableImageTransformer : Transformer<LoadableImageTransformerInput, LoadableImage> {

    // Consider these as empty images
    private val blacklistedPatterns = setOf(
        "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available",
        "http://i.annihil.us/u/prod/marvel/i/mg/f/60/4c002e0305708"
    )

    override fun transform(input: LoadableImageTransformerInput): LoadableImage {
        val imageUrl = input.networkImage?.let {
            extractImageUrl(it)
        }
        return LoadableImage(
            imageUrl = imageUrl,
            defaultEntity = input.defaultImageType
        )
    }

    private fun extractImageUrl(networkImage: NetworkImage): String? {
        if (blacklistedPatterns.contains(networkImage.path)) {
            return null
        }
        return "${networkImage.path}.${networkImage.extension}".replace("http://", "https://")
    }
}