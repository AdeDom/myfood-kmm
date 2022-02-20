package com.adedom.core.data.resource.remote.auth

import com.adedom.core.data.models.request.login.LoginRequest
import com.adedom.core.data.models.response.base.BaseResponse
import com.adedom.core.data.models.response.token.TokenResponse

interface AuthRemoteDataSource {

    suspend fun callLogin(loginRequest: LoginRequest): BaseResponse<TokenResponse>
}