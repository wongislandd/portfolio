package com.wongislandd.portfolio.programs.infinityindex.infra.paging

import com.wongislandd.portfolio.programs.infinityindex.infra.networking.NetworkClient
import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.DataWrapper
import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.NetworkDataWrapper
import com.wongislandd.portfolio.programs.infinityindex.infra.transformers.DataWrapperTransformer
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityModel
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import com.wongislandd.portfolio.programs.infinityindex.infra.util.Resource
import com.wongislandd.portfolio.programs.infinityindex.infra.util.Resource.Loading.onSuccess
import com.wongislandd.portfolio.programs.infinityindex.infra.util.safeLet
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.AppLeveled
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.util.reflect.TypeInfo

abstract class BaseRepository<NETWORK_MODEL, LOCAL_MODEL : EntityModel>(
    private val transformer: DataWrapperTransformer<NETWORK_MODEL, LOCAL_MODEL>,
    okHttpClient: HttpClient,
    private val rootEntityType: EntityType,
    private val typeInfo: TypeInfo
) : NetworkClient(okHttpClient) {

    suspend fun getAll(
        start: Int,
        count: Int,
        searchParam: String?,
        sortKey: String?,
        additionalParams: Map<String, Any> = emptyMap(),
    ): Resource<DataWrapper<LOCAL_MODEL>> {
        val response: Resource<NetworkDataWrapper<NETWORK_MODEL>> =
            makeRequest(rootEntityType.key, typeInfo) {
                attachBasicPagingParams(
                    searchParam,
                    sortKey,
                    start,
                    count,
                    additionalParams
                )
            }
        return handleResponse(response)
    }


    /**
     * If this is the Comics Repository, I can use this to find comics
     * related to another entity. This is designed this way so that this repository
     * only needs to know how to transform one type of entity.
     */
    suspend fun getPagedPrimaryEntityRelatedToOtherEntity(
        relatedEntityType: EntityType,
        relatedEntityId: Int,
        searchParam: String? = null,
        sortKey: String? = null,
        start: Int,
        count: Int,
        additionalParams: Map<String, Any> = emptyMap(),
    ): Resource<DataWrapper<LOCAL_MODEL>> {
        val response: Resource<NetworkDataWrapper<NETWORK_MODEL>> =
            makeRequest(
                "${relatedEntityType.key}/$relatedEntityId/${rootEntityType.key}",
                typeInfo
            ) {
                attachBasicPagingParams(
                    searchParam,
                    sortKey,
                    start,
                    count,
                    additionalParams
                )
            }
        return handleResponse(response)
    }

    suspend fun get(
        id: Int
    ): Resource<LOCAL_MODEL> {
        val response: Resource<NetworkDataWrapper<NETWORK_MODEL>> =
            makeRequest("${rootEntityType.key}/$id", typeInfo)
        return handleResponse(response).map {
            it.data.results.firstOrNull()
        }
    }

    private fun handleResponse(response: Resource<NetworkDataWrapper<NETWORK_MODEL>>): Resource<DataWrapper<LOCAL_MODEL>> {
        if (AppLeveled.attributionText.value == null) {
            response.onSuccess { AppLeveled.updateAttributionText(it.attributionText) }
        }
        return response.map { transformer.transform(it) }
    }

    private fun HttpRequestBuilder.attachBasicPagingParams(
        searchParam: String? = null,
        sortKey: String? = null,
        start: Int,
        count: Int,
        additionalParams: Map<String, Any> = emptyMap(),
    ) {
        parameter("offset", start)
        parameter("limit", count)
        parameter("orderBy", sortKey)
        safeLet(
            searchParam?.takeIf { it.isNotBlank() },
            rootEntityType.searchParamType?.key
        ) { searchQuery, searchParamType ->
            parameter(searchParamType, searchQuery)
        }
        additionalParams.forEach {
            parameter(it.key, it.value)
        }
    }
}