package com.adedom.core.base

import com.adedom.core.data.models.response.base.BaseError
import com.adedom.core.data.models.response.base.BaseResponse
import com.adedom.core.data.repositories.Resource
import com.adedom.core.data.resource.exception.ApiServiceManagerException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

abstract class BaseRepository {

    suspend fun <T : Any> callApi(
        service: suspend () -> BaseResponse<T>
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response = service()
                if (response.result != null) {
                    Resource.Success(response.result!!)
                } else {
                    val messageString = response.error?.message.orEmpty()
                    val baseError = BaseError(message = messageString)
                    val error = response.error ?: baseError
                    Resource.Error(error)
                }
            } catch (e: ApiServiceManagerException) {
                val jsonString = e.message.orEmpty()
                val baseError = Json.decodeFromString<BaseError>(jsonString)
                Resource.Error(baseError)
            } catch (throwable: Throwable) {
                val messageString = throwable.message ?: "Error."
                val baseError = BaseError(message = messageString)
                Resource.Error(baseError)
            }
        }
    }

    suspend fun <T : Any> callMultipleApi(
        service: suspend CoroutineScope.() -> Resource<T>
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                service()
            } catch (e: ApiServiceManagerException) {
                val jsonString = e.message.orEmpty()
                val baseError = Json.decodeFromString<BaseError>(jsonString)
                Resource.Error(baseError)
            } catch (throwable: Throwable) {
                val messageString = throwable.message ?: "Error."
                val baseError = BaseError(message = messageString)
                Resource.Error(baseError)
            }
        }
    }
}