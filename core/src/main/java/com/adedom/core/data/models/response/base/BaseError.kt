package com.adedom.core.data.models.response.base

@kotlinx.serialization.Serializable
data class BaseError(
    val code: String? = null,
    val message: String,
)