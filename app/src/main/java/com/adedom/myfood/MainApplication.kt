package com.adedom.myfood

import android.app.Application
import com.adedom.core.di.*
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.androidXModule

class MainApplication : Application(), DIAware {

    override val di by DI.lazy {
        import(androidXModule(this@MainApplication))

        importAll(
            dataSourceProviderModule,
            remoteDataSourceModule,
            repositoryModule,
            domainModule,
            presentationModule,
        )
    }
}