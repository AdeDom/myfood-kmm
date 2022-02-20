package com.adedom.core.domain.usecase.login

import com.adedom.core.data.models.request.login.LoginRequest
import com.adedom.core.data.repositories.Resource
import com.adedom.core.data.repositories.auth.AuthRepository

class LoginUseCase(
    private val authRepository: AuthRepository,
) {

    suspend operator fun invoke(username: String, password: String): Resource<Unit> {
        val loginRequest = LoginRequest(username, password)
        return authRepository.callLogin(loginRequest)
    }
}