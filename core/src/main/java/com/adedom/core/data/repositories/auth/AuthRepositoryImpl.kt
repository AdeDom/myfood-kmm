package com.adedom.core.data.repositories.auth

import com.adedom.core.data.models.request.login.LoginRequest
import com.adedom.core.data.models.response.base.BaseError
import com.adedom.core.data.repositories.Resource
import com.adedom.core.data.resource.remote.auth.AuthRemoteDataSource
import com.adedom.core.data.store.AppStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepositoryImpl(
    private val appStore: AppStore,
    private val authRemoteDataSource: AuthRemoteDataSource,
) : AuthRepository {

    override suspend fun callTestAuth(): Resource<String> {
        return withContext(Dispatchers.IO) {
            try {
                val response = authRemoteDataSource.callTestAuth()
                Resource.Success(response.result.orEmpty())
            } catch (e: Throwable) {
                val message = e.message ?: "Error"
                val baseError = BaseError(message = message)
                Resource.Error(baseError)
            }
        }
    }

    override suspend fun callLogin(loginRequest: LoginRequest): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val response = authRemoteDataSource.callLogin(loginRequest)
                val accessToken = response.result?.accessToken
                val refreshToken = response.result?.refreshToken
                appStore.accessToken = accessToken
                appStore.refreshToken = refreshToken
                Resource.Success(Unit)
            } catch (e: Throwable) {
                val message = e.message ?: "Error"
                val baseError = BaseError(message = message)
                Resource.Error(baseError)
            }
        }
    }
}