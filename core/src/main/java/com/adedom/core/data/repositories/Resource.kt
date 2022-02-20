package com.adedom.core.data.repositories

import com.adedom.core.data.models.response.base.BaseError

sealed class Resource<out T : Any> {
    data class Success<out T : Any>(val data: T) : Resource<T>()
    data class Error(val error: BaseError) : Resource<Nothing>()
}