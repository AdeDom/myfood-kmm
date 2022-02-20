package com.adedom.core.data.models.response.token

@kotlinx.serialization.Serializable
data class TokenResponse(
    val accessToken: String,
    val refreshToken: String,
)