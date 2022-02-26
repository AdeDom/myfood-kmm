package com.adedom.myfood

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.adedom.core.data.store.AppStore
import com.adedom.core.di.*
import com.adedom.myfood.data.store.AppStoreImpl
import com.adedom.myfood.di.databaseModule
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.bindSingleton
import org.kodein.di.instance

class MainApplication : Application(), DIAware {

    override val di by DI.lazy {
        import(androidXModule(this@MainApplication))

        importAll(
            databaseModule,
            localDataSourceModule,
            dataSourceProviderModule,
            remoteDataSourceModule,
            repositoryModule,
            domainModule,
            presentationModule,
        )

        bindSingleton<SharedPreferences> {
            instance<Context>().getSharedPreferences("AppStore", Context.MODE_PRIVATE)
        }
        bindSingleton<AppStore> { AppStoreImpl(instance()) }
    }
}