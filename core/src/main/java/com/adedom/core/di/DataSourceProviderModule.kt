package com.adedom.core.di

import com.adedom.core.data.resource.interceptor.ApiServiceManagerInterceptor
import com.adedom.core.data.resource.remote.DataSourceProvider
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val dataSourceProviderModule = DI.Module(name = "data_source_provider") {

    bindSingleton { ApiServiceManagerInterceptor() }
    bindSingleton { DataSourceProvider(instance()) }
}