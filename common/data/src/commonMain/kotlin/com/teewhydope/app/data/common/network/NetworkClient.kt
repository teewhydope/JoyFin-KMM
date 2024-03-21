package com.teewhydope.app.data.common.network

import com.teewhydope.app.logger.globalLogger
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import kotlinx.serialization.json.Json

expect val httpClientEngineConfig: HttpClientEngineFactory<HttpClientEngineConfig>

class NetworkClient {
    fun build(): HttpClient {
        return HttpClient(httpClientEngineConfig) {
            installLogging()
            installJsonFeature()

        }
    }

    private fun HttpClientConfig<*>.installJsonFeature() =
        install(ContentNegotiation) {
            register(
                ContentType.Text.Any, KotlinxSerializationConverter(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            )

        }

    private fun HttpClientConfig<*>.installLogging() =
        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    globalLogger.d(message)
                }
            }
        }
}