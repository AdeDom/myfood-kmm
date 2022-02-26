package com.adedom.core.di

import com.adedom.core.data.resource.local.MyFoodLocalDataSource
import com.adedom.core.data.resource.local.MyFoodLocalDataSourceImpl
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val localDataSourceModule = DI.Module(name = "local_data_source") {

    bindSingleton<MyFoodLocalDataSource> { MyFoodLocalDataSourceImpl(instance()) }
}