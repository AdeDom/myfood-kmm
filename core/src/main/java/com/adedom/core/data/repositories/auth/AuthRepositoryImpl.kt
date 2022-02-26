package com.adedom.core.data.repositories.auth

import com.adedom.core.base.BaseRepository
import com.adedom.core.data.models.request.login.LoginRequest
import com.adedom.core.data.repositories.Resource
import com.adedom.core.data.resource.remote.auth.AuthRemoteDataSource
import com.adedom.core.data.store.AppStore

class AuthRepositoryImpl(
    private val appStore: AppStore,
    private val authRemoteDataSource: AuthRemoteDataSource,
) : BaseRepository(), AuthRepository {

    override suspend fun callTestAuth(): Resource<String> {
        return callMultipleApi {
            val response = authRemoteDataSource.callTestAuth()
            Resource.Success(response.result.orEmpty())
        }
    }

    override suspend fun callLogin(loginRequest: LoginRequest): Resource<Unit> {
        return callMultipleApi {
            val response = authRemoteDataSource.callLogin(loginRequest)
            val accessToken = response.result?.accessToken
            val refreshToken = response.result?.refreshToken
            appStore.accessToken = accessToken
            appStore.refreshToken = refreshToken
            Resource.Success(Unit)
        }
    }
}