package com.adedom.core.domain.usecase.test_auth

import com.adedom.core.data.repositories.Resource
import com.adedom.core.data.repositories.auth.AuthRepository

class TestAuthUseCase(
    private val authRepository: AuthRepository,
) {

    suspend operator fun invoke(): Resource<String> {
        return authRepository.callTestAuth()
    }
}