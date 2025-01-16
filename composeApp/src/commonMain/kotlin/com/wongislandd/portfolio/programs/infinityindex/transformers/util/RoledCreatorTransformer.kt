package com.wongislandd.portfolio.programs.infinityindex.transformers.util

import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.NetworkList
import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.RoledNetworkSummary
import com.wongislandd.portfolio.programs.infinityindex.infra.util.Transformer
import com.wongislandd.portfolio.programs.infinityindex.infra.util.safeLet

data class RoledCreatorOutput(
    val primaryCreators: Map<String, List<String>>,
    val coverCreators: Map<String, List<String>>
)

class RoledCreatorTransformer : Transformer<NetworkList<RoledNetworkSummary>, RoledCreatorOutput> {
    override fun transform(input: NetworkList<RoledNetworkSummary>): RoledCreatorOutput {
        val comicCreators = mutableMapOf<String, MutableList<String>>()
        val coverCreators = mutableMapOf<String, MutableList<String>>()
        input.items?.forEach { networkSummary ->
            safeLet(networkSummary.name, networkSummary.role) { name, role ->
                val isCoverRole = role.contains("cover", ignoreCase = true)
                val adjustedRole = role.capitalizeEachWord()
                if (isCoverRole) {
                    coverCreators[adjustedRole]?.add(name) ?: coverCreators.set(
                        adjustedRole,
                        mutableListOf(name)
                    )
                } else {
                    comicCreators[adjustedRole]?.add(name) ?: comicCreators.set(
                        adjustedRole,
                        mutableListOf(name)
                    )
                }
            }
        }
        return RoledCreatorOutput(comicCreators, coverCreators)
    }
}