package com.adedom.core.data.resource.remote

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

class DataSourceProvider {

    fun getBaseUrl(): String {
        return "https://myfood-server.herokuapp.com/"
    }

    fun getHttpClient(dataSourceType: DataSourceType): HttpClient {
        return HttpClient(OkHttp) {
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 60_000
            }

            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.HEADERS
            }
        }
    }
}