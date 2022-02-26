package com.adedom.core.di

import com.adedom.core.domain.usecase.login.LoginUseCase
import com.adedom.core.domain.usecase.test_auth.TestAuthUseCase
import com.adedom.core.domain.usecase.test_db.TestDatabaseUseCase
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val domainModule = DI.Module(name = "domain") {

    bindProvider { LoginUseCase(instance()) }
    bindProvider { TestAuthUseCase(instance()) }
    bindProvider { TestDatabaseUseCase(instance()) }
}