package com.adedom.myfood.di

import com.adedom.myfood.MyFoodDatabase
import com.adedom.myfood.data.db.MyFoodDatabaseDriverFactory
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val databaseModule = DI.Module(name = "database") {

    bindSingleton { MyFoodDatabaseDriverFactory(instance()).createDriver() }
    bindSingleton { MyFoodDatabase(instance()) }
}