package com.wongislandd.portfolio.programs.infinityindex.transformers.util

import com.wongislandd.portfolio.programs.infinityindex.models.util.RelatedPrice
import com.wongislandd.portfolio.programs.infinityindex.models.network.NetworkComicPrice
import com.wongislandd.portfolio.programs.infinityindex.infra.util.Transformer
import com.wongislandd.portfolio.programs.infinityindex.infra.util.safeLet

class RelatedPricesTransformer(
    private val networkFieldTypeMapper: NetworkFieldTypeMapper
) : Transformer<List<NetworkComicPrice>, List<RelatedPrice>> {

    override fun transform(input: List<NetworkComicPrice>): List<RelatedPrice> {
        return input.mapNotNull { networkUrl ->
            val associatedType = networkUrl.type?.let { networkFieldTypeMapper.mapPriceType(it) }
            safeLet(associatedType, networkUrl.price) { type, price ->
                RelatedPrice(type, formatToDollarAmount(price))
            }
        }
    }

    private fun formatToDollarAmount(amount: Float): String {
        val dollars = amount.toInt()
        val cents = ((amount - dollars) * 100).toInt()
        return "$${dollars}.${if (cents < 10) "0$cents" else cents}"
    }

}