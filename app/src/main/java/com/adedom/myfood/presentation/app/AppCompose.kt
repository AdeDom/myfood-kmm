package com.adedom.myfood.presentation.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.adedom.myfood.presentation.login.LoginScreen
import com.adedom.myfood.presentation.splash_screen.SplashScreenCompose

@Composable
fun AppCompose() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = "splash_screen"
        ) {
            composable("splash_screen") {
                SplashScreenCompose {
                    navController.navigate("login")
                }
            }
            composable("login") {
                LoginScreen()
            }
        }
    }
}