package com.adedom.core.data.resource.interceptor

import com.adedom.core.data.models.response.base.BaseError
import com.adedom.core.utility.constant.ResponseKeyConstant
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Serializable
data class ApiServiceResponse(
    var version: String = ResponseKeyConstant.VERSION,
    var status: String = ResponseKeyConstant.ERROR,
    var error: BaseError? = null,
)

fun String.decodeApiServiceResponseFromString(): ApiServiceResponse {
    return Json {
        ignoreUnknownKeys = true
    }.decodeFromString(this)
}