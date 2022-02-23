package com.adedom.myfood.presentation.splash_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.adedom.core.presentation.splash_screen.flow.SplashScreenFlow
import org.kodein.di.compose.rememberInstance

@Composable
fun SplashScreenCompose(finish: () -> Unit) {
    val flow: SplashScreenFlow by rememberInstance()
    val state by flow.viewState.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        if (state.isFinish) {
            finish()
        }
        Text(text = "Welcome to my food.")
    }

    DisposableEffect(Unit) {
        onDispose {
            flow.onCleared()
        }
    }
}