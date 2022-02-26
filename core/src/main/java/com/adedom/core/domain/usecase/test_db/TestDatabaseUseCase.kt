package com.adedom.core.domain.usecase.test_db

import com.adedom.core.data.resource.local.MyFoodLocalDataSource
import kotlinx.coroutines.flow.Flow
import myfood.database.MyFoodEntity

class TestDatabaseUseCase(
    private val myFoodLocalDataSource: MyFoodLocalDataSource,
) {

    fun getMyFoodAll(): Flow<List<MyFoodEntity>> {
        return myFoodLocalDataSource.getMyFoodAll()
    }

    suspend fun insertMyFood() {
        myFoodLocalDataSource.insertMyFood(
            id = System.currentTimeMillis(),
            food = ('A'..'Z').random().toString(),
        )
    }

    suspend fun deleteMyFoodAll() {
        myFoodLocalDataSource.deleteMyFoodAll()
    }
}