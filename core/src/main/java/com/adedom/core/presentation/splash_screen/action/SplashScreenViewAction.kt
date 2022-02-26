package com.adedom.core.presentation.splash_screen.action

sealed class SplashScreenViewAction {
    object Initial : SplashScreenViewAction()
    object OnClickTestAuth : SplashScreenViewAction()
}