package com.adedom.core.di

import com.adedom.core.data.resource.remote.DataSourceProvider
import org.kodein.di.DI
import org.kodein.di.bindSingleton

val dataSourceProviderModule = DI.Module(name = "data_source_provider") {

    bindSingleton { DataSourceProvider() }
}