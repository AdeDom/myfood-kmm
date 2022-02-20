package com.adedom.core.di

import com.adedom.core.domain.usecase.main.MainUseCase
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val domainModule = DI.Module(name = "domain") {

    bindProvider { MainUseCase(instance()) }
}