package com.adedom.core.domain.usecase.test_db

import com.adedom.core.data.resource.local.MyFoodLocalDataSource

class TestDatabaseUseCase(
    private val myFoodLocalDataSource: MyFoodLocalDataSource,
) {

    suspend fun insertMyFood() {
        myFoodLocalDataSource.insertMyFood(
            id = System.currentTimeMillis(),
            food = ('A'..'Z').random().toString(),
        )
    }
}