package com.wongislandd.portfolio.programs.infinityindex.infra.networking

import co.touchlab.kermit.Logger
import com.wongislandd.portfolio.programs.infinityindex.infra.util.NetworkError
import com.wongislandd.portfolio.programs.infinityindex.infra.util.Resource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.util.network.UnresolvedAddressException
import io.ktor.util.reflect.TypeInfo
import kotlinx.serialization.SerializationException

abstract class NetworkClient(val httpClient: HttpClient) {

    suspend inline fun <reified T> makeRequest(
        endpoint: String,
        typeInfo: TypeInfo,
        builder: HttpRequestBuilder.() -> Unit = {},
    ): Resource<T> {
        return makeNetworkRequest(endpoint, typeInfo, builder)
    }

    suspend inline fun <reified T> makeNetworkRequest(
        endpoint: String,
        typeInfo: TypeInfo,
        builder: HttpRequestBuilder.() -> Unit = {}
    ): Resource<T> {
        try {
            val response = httpClient.get {
                url(endpoint)
                builder()
            }
            val newValue = when (response.status.value) {
                in 200..299 -> {
                    // This is not making use of ktor client, find a way. It seemed faster.
                    val data: T = response.body(typeInfo)
                    Resource.Success(data)
                }

                401 -> Resource.Error(NetworkError.UNAUTHORIZED)
                404 -> Resource.Error(NetworkError.NOT_FOUND)
                409 -> Resource.Error(NetworkError.CONFLICT)
                408 -> Resource.Error(NetworkError.REQUEST_TIMEOUT)
                413 -> Resource.Error(NetworkError.PAYLOAD_TOO_LARGE)
                429 -> Resource.Error(NetworkError.MARVEL_API_RATE_LIMITED)
                in 500..599 -> Resource.Error(NetworkError.SERVER_ERROR)
                else -> Resource.Error(NetworkError.UNKNOWN)
            }
            return newValue
        } catch (e: UnresolvedAddressException) {
            return Resource.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            return Resource.Error(NetworkError.SERIALIZATION)
        } catch (e: SocketTimeoutException) {
            return Resource.Error(NetworkError.REQUEST_TIMEOUT)
        } catch (e: Exception) {
            Logger.e(tag = "Network Error", null) {
                e.toString()
            }
            return Resource.Error(NetworkError.UNKNOWN, e)
        }
    }
}