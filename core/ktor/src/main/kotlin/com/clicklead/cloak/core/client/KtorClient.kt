package com.clicklead.cloak.core.client

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorClient {
    fun provideHttpClient() = HttpClient(Android) {
        install(ContentNegotiation) {
            json(
                json = Json {
                    ignoreUnknownKeys = true
                    encodeDefaults = true
                    isLenient = true
                    allowSpecialFloatingPointValues = true
                    prettyPrint = false
                    useArrayPolymorphism = false
                },
                contentType = ContentType.Application.Json,
            )
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 1000 * 60
            connectTimeoutMillis = 1000 * 10
            socketTimeoutMillis = 3000
        }
        defaultRequest {
            url {
                host = "anika1d.github.io/SITES/"
                protocol = URLProtocol.HTTPS
            }
        }
    }
}