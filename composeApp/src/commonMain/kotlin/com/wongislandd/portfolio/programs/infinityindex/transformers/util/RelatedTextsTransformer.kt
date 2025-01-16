package com.wongislandd.portfolio.programs.infinityindex.transformers.util

import com.wongislandd.portfolio.programs.infinityindex.models.util.RelatedText
import com.wongislandd.portfolio.programs.infinityindex.models.network.NetworkTextObject
import com.wongislandd.portfolio.programs.infinityindex.infra.util.Transformer
import com.wongislandd.portfolio.programs.infinityindex.infra.util.safeLet

class RelatedTextsTransformer(
    private val networkFieldTypeMapper: NetworkFieldTypeMapper
) : Transformer<List<NetworkTextObject>, List<RelatedText>> {

    override fun transform(input: List<NetworkTextObject>): List<RelatedText> {
        return input.mapNotNull { networkUrl ->
            val associatedType = networkUrl.type?.let { networkFieldTypeMapper.mapTextType(it) }
            safeLet(associatedType, networkUrl.text) { type, text ->
                RelatedText(type, text)
            }
        }
    }

}