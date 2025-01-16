package com.wongislandd.portfolio.programs.infinityindex.transformers.util

import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.EntityReference
import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.NetworkSummary
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import com.wongislandd.portfolio.programs.infinityindex.infra.util.Transformer
import com.wongislandd.portfolio.programs.infinityindex.infra.util.safeLet

class EntityReferenceTransformer : Transformer<NetworkSummary, EntityReference?> {
    override fun transform(input: NetworkSummary): EntityReference? {
        return safeLet(input.resourceURI, input.name) { resourceURI, name ->
            val entityTypeStr = extractPathBeforeId(resourceURI)
            val entityId = resourceURI.substringAfterLast("/").substringAfterLast("/").toInt()
            val identifiedEntityType = EntityType.entries.find { it.key == entityTypeStr }
            identifiedEntityType?.let {
                EntityReference(it, entityId, name)
            }
        }
    }

    private fun extractPathBeforeId(url: String): String? {
        val regex = Regex(".*/([^/]+)/\\d+$")
        val matchResult = regex.find(url)
        return matchResult?.groupValues?.get(1)
    }
}