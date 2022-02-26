package com.adedom.core.data.resource.interceptor

import com.adedom.core.data.models.request.token.TokenRequest
import com.adedom.core.data.models.response.base.BaseError
import com.adedom.core.data.models.response.base.BaseResponse
import com.adedom.core.data.models.response.error.ErrorResponse
import com.adedom.core.data.models.response.token.TokenResponse
import com.adedom.core.data.resource.exception.ApiServiceManagerException
import com.adedom.core.data.store.AppStore
import com.adedom.core.utility.constant.ResponseKeyConstant
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.http.content.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class ApiServiceManagerInterceptor(
    private val appStore: AppStore,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        if (response.isSuccessful) {
            val jsonString = response.peekBody(Long.MAX_VALUE).string()
            val baseResponse = jsonString.decodeApiServiceResponseFromString()
            val status = baseResponse.status
            return if (status == ResponseKeyConstant.SUCCESS) {
                response
            } else {
                val baseError = baseResponse.error
                verifyError(baseError, chain)
            }
        } else {
            try {
                val jsonString = JSONObject(response.body?.string().orEmpty()).toString()
                val baseResponse = jsonString.decodeApiServiceResponseFromString()
                val baseError = baseResponse.error
                return verifyError(baseError, chain)
            } catch (e: JSONException) {
                val messageString = "Error."
                val baseError = BaseError(message = messageString)
                val jsonString = Json.encodeToString(baseError)
                throw ApiServiceManagerException(jsonString)
            }
        }
    }

    private fun verifyError(baseError: BaseError?, chain: Interceptor.Chain): Response {
        return when (baseError?.code) {
            ErrorResponse.AccessTokenError.code -> {
                val hasRefreshToken = appStore.refreshToken.isNullOrBlank()
                if (hasRefreshToken) {
                    appStore.accessToken = ""
                    appStore.refreshToken = ""
                    val jsonString = Json.encodeToString(baseError)
                    throw ApiServiceManagerException(jsonString)
                } else {
                    callRefreshToken(baseError, chain)
                }
            }
            ErrorResponse.RefreshTokenError.code -> {
                appStore.accessToken = ""
                appStore.refreshToken = ""
                val jsonString = Json.encodeToString(baseError)
                throw ApiServiceManagerException(jsonString)
            }
            else -> {
                val hasError = baseError != null
                if (hasError) {
                    val jsonString = Json.encodeToString(baseError)
                    throw ApiServiceManagerException(jsonString)
                } else {
                    val messageString = "The server response was not found."
                    throw IOException(messageString)
                }
            }
        }
    }

    private fun callRefreshToken(baseError: BaseError, chain: Interceptor.Chain): Response {
        val tokenRequest = TokenRequest(
            accessToken = appStore.accessToken,
            refreshToken = appStore.refreshToken,
        )

        val tokenResponse: BaseResponse<TokenResponse> = runBlocking {
            try {
                getHttpClient()
                    .post("https://myfood-server.herokuapp.com/api/auth/refreshtoken") {
                        body = TextContent(
                            text = Json.encodeToString(tokenRequest),
                            contentType = ContentType.Application.Json
                        )
                    }
            } catch (e: Throwable) {
                e.printStackTrace()
                appStore.accessToken = ""
                appStore.refreshToken = ""
                val jsonString = Json.encodeToString(baseError)
                throw ApiServiceManagerException(jsonString)
            }
        }

        return if (tokenResponse.status == ResponseKeyConstant.SUCCESS) {
            val accessToken = tokenResponse.result?.accessToken
            val refreshToken = tokenResponse.result?.refreshToken
            appStore.accessToken = accessToken
            appStore.refreshToken = refreshToken
            intercept(chain)
        } else {
            val error = tokenResponse.error
            val jsonString = Json.encodeToString(error)
            throw ApiServiceManagerException(jsonString)
        }
    }

    private fun getHttpClient(): HttpClient {
        return HttpClient(OkHttp) {
            engine {
                addInterceptor { chain ->
                    val request = chain.request()
                        .newBuilder()
                        .addHeader("my-food-key", appStore.accessToken.orEmpty())
                        .build()
                    chain.proceed(request)
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