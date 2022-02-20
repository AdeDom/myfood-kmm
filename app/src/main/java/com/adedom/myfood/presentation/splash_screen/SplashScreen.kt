package com.adedom.myfood.presentation.splash_screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.adedom.core.presentation.splash_screen.flow.SplashScreenFlow
import org.kodein.di.compose.rememberInstance

@Composable
fun SplashScreenCompose(finish: () -> Unit) {
    val flow: SplashScreenFlow by rememberInstance()
    val state = flow.viewState.collectAsState()

    if (state.value.isFinish) {
        finish()
    } else {
        Text(text = "Welcome to my food.")
    }
}