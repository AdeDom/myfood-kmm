package com.adedom.core.di

import com.adedom.myfood.MyFoodDatabase
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val databaseModule = DI.Module(name = "database") {

    bindSingleton { MyFoodDatabase(instance()) }
}