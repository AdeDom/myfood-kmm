package com.adedom.core.data.repositories.auth

import com.adedom.core.data.models.request.login.LoginRequest
import com.adedom.core.data.repositories.Resource

interface AuthRepository {

    suspend fun callLogin(loginRequest: LoginRequest): Resource<Unit>
}