package com.adedom.core.data.resource.remote.auth

import com.adedom.core.data.models.request.login.LoginRequest
import com.adedom.core.data.models.response.base.BaseResponse
import com.adedom.core.data.models.response.token.TokenResponse
import com.adedom.core.data.resource.remote.DataSourceProvider
import com.adedom.core.data.resource.remote.DataSourceType
import io.ktor.client.request.*
import io.ktor.content.*
import io.ktor.http.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class AuthRemoteDataSourceImpl(
    private val dataSourceProvider: DataSourceProvider,
) : AuthRemoteDataSource {

    override suspend fun callLogin(loginRequest: LoginRequest): BaseResponse<TokenResponse> {
        return dataSourceProvider.getHttpClient(DataSourceType.UN_AUTHORIZATION)
            .post(dataSourceProvider.getBaseUrl() + "api/auth/login") {
                body = TextContent(
                    text = Json.encodeToString(loginRequest),
                    contentType = ContentType.Application.Json
                )
            }
    }
}