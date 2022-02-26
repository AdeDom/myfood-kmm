package com.adedom.core.di

import com.adedom.core.data.repositories.auth.AuthRepository
import com.adedom.core.data.repositories.auth.AuthRepositoryImpl
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val repositoryModule = DI.Module(name = "repository") {

    bindSingleton<AuthRepository> { AuthRepositoryImpl(instance(), instance()) }
}