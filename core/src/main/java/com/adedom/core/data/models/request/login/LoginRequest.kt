package com.adedom.core.data.models.request.login

@kotlinx.serialization.Serializable
data class LoginRequest(
    val username: String?,
    val password: String?,
)