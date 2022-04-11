package com.adedom.myfood.presentation.splash_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.adedom.core.presentation.splash_screen.flow.SplashScreenFlow
import org.kodein.di.compose.rememberInstance

@Composable
fun SplashScreenCompose(finish: () -> Unit) {
    val flow: SplashScreenFlow by rememberInstance()
    val state by flow.viewState.collectAsState()

//    Surface(
//        modifier = Modifier.fillMaxSize(),
//        color = MaterialTheme.colors.background
//    ) {
//        if (state.isFinish) {
//            finish()
//        }
//        Text(text = "Welcome to my food.")
//    }

    Column {
        state.myFoods.forEach { myFoodEntity ->
            Text(text = "Database ${myFoodEntity.id}, ${myFoodEntity.food}")
        }
        Button(onClick = flow::setOnClickInsertMyFoodAction) {
            Text(text = "Insert")
        }
        Button(onClick = flow::setOnClickDeleteMyFoodAction) {
            Text(text = "Delete")
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            flow.onCleared()
        }
    }
}