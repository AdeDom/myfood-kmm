package com.adedom.core.data.resource.remote

import com.adedom.core.data.resource.interceptor.ApiServiceManagerInterceptor
import com.adedom.core.data.store.AppStore
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

class DataSourceProvider(
    private val appStore: AppStore,
    private val apiServiceManagerInterceptor: ApiServiceManagerInterceptor,
) {

    fun getBaseUrl(): String {
        return "https://myfood-server.herokuapp.com/"
    }

    fun getHttpClient(dataSourceType: DataSourceType): HttpClient {
        return HttpClient(OkHttp) {
            engine {
                addInterceptor(apiServiceManagerInterceptor)
                if (dataSourceType == DataSourceType.AUTHORIZATION) {
                    addInterceptor { chain ->
                        val request = chain.request()
                            .newBuilder()
                            .addHeader("my-food-key", appStore.accessToken.orEmpty())
                            .build()
                        chain.proceed(request)
                    }
                }
            }

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