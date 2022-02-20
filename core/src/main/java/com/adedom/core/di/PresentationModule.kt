package com.adedom.core.di

import com.adedom.core.presentation.login.flow.LoginFlow
import com.adedom.core.presentation.main.flow.MainFlow
import com.adedom.core.presentation.splash_screen.flow.SplashScreenFlow
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val presentationModule = DI.Module(name = "presentation") {

    bindProvider { MainFlow(instance()) }
    bindProvider { SplashScreenFlow() }
    bindProvider { LoginFlow() }
}