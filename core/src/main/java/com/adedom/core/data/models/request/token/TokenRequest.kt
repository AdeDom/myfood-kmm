package com.adedom.core.data.models.request.token

@kotlinx.serialization.Serializable
data class TokenRequest(
    val accessToken: String?,
    val refreshToken: String?,
)