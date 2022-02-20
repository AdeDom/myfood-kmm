package com.adedom.core.di

import com.adedom.core.data.resource.remote.auth.AuthRemoteDataSource
import com.adedom.core.data.resource.remote.auth.AuthRemoteDataSourceImpl
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val remoteDataSourceModule = DI.Module(name = "remote_data_source") {

    bindSingleton<AuthRemoteDataSource> { AuthRemoteDataSourceImpl(instance()) }
}