package com.adedom.core.data.resource.interceptor

import com.adedom.core.data.models.response.base.BaseError
import com.adedom.core.data.models.response.error.ErrorResponse
import com.adedom.core.data.resource.exception.ApiServiceManagerException
import com.adedom.core.utility.constant.ResponseKeyConstant
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ApiServiceManagerInterceptor : Interceptor {

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
            val jsonString = response.body?.string().orEmpty()
            val baseResponse = jsonString.decodeApiServiceResponseFromString()
            val baseError = baseResponse.error
            return verifyError(baseError, chain)
        }
    }

    private fun verifyError(error: BaseError?, chain: Interceptor.Chain): Response {
        return when (error?.code) {
            ErrorResponse.AccessTokenError.code -> {
                val jsonString = Json.encodeToString(error)
                throw ApiServiceManagerException(jsonString)
            }
            ErrorResponse.RefreshTokenError.code -> {
                val jsonString = Json.encodeToString(error)
                throw ApiServiceManagerException(jsonString)
            }
            else -> {
                val hasErrorCode = error != null
                if (hasErrorCode) {
                    val jsonString = Json.encodeToString(error)
                    throw ApiServiceManagerException(jsonString)
                } else {
                    val messageString = "The server response was not found."
                    throw IOException(messageString)
                }
            }
        }
    }
}