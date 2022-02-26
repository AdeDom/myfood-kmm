package com.adedom.core.presentation.splash_screen.state

import myfood.database.MyFoodEntity

data class SplashScreenViewState(
    val isFinish: Boolean = false,
    val myFoods: List<MyFoodEntity> = emptyList(),
)