package com.adedom.core.di

import com.adedom.core.data.repositories.MainRepository
import com.adedom.core.data.repositories.MainRepositoryImpl
import org.kodein.di.DI
import org.kodein.di.bindSingleton

val repositoryModule = DI.Module(name = "repository") {

    bindSingleton<MainRepository> { MainRepositoryImpl() }
}