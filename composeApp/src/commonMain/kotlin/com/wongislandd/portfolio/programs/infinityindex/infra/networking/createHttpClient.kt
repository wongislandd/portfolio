package com.wongislandd.portfolio.programs.infinityindex.infra.networking

import com.wongislandd.portfolio.BuildKonfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.datetime.Clock
import kotlinx.serialization.json.Json
import okio.ByteString.Companion.encodeUtf8


private const val BASE_URL = "https://gateway.marvel.com/v1/public/"
private const val API_KEY_QUERY_PARAM = "apikey"
private const val TIMESTAMP_QUERY_PARAM = "ts"
private const val HASH_QUERY_PARAM = "hash"

fun createInfinityIndexHttpClient(engine: HttpClientEngine): HttpClient {
    return HttpClient(engine) {
        defaultRequest {
            val timestamp = Clock.System.now().toString()
            url(BASE_URL)
            url.parameters.append(TIMESTAMP_QUERY_PARAM, timestamp)
            url.parameters.append(API_KEY_QUERY_PARAM, BuildKonfig.PUBLIC_API_KEY)
            url.parameters.append(HASH_QUERY_PARAM, hash(timestamp))
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    co.touchlab.kermit.Logger.withTag("Network").d {
                        message
                    }
                }
            }
            level = LogLevel.BODY
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 15000
        }
        install(HttpCache)
        install(ContentNegotiation) {
            json(
                json = Json {
                    prettyPrint = true
                    ignoreUnknownKeys = true
                    isLenient = true
                    explicitNulls = false
                }
            )
        }
    }
}

private fun hash(timestamp: String): String {
    val combined = "${timestamp}${BuildKonfig.PRIVATE_API_KEY}${BuildKonfig.PUBLIC_API_KEY}"
    return combined.encodeUtf8().md5().hex()
}