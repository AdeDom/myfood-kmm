package com.adedom.core.di

import com.adedom.core.presentation.main.flow.MainFlow
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val presentationModule = DI.Module(name = "presentation") {

    bindProvider { MainFlow(instance()) }
}