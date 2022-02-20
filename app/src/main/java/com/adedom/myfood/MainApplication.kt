package com.adedom.myfood

import android.app.Application
import com.adedom.core.di.domainModule
import com.adedom.core.di.presentationModule
import com.adedom.core.di.repositoryModule
import org.kodein.di.DI
import org.kodein.di.DIAware

class MainApplication : Application(), DIAware {

    override val di by DI.lazy {
        importAll(
            repositoryModule,
            domainModule,
            presentationModule,
        )
    }
}