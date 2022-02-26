package com.adedom.core.data.resource.local

import kotlinx.coroutines.flow.Flow
import myfood.database.MyFoodEntity

interface MyFoodLocalDataSource {

    suspend fun getMyFoodById(id: Long): MyFoodEntity?

    fun getMyFoodAll(): Flow<List<MyFoodEntity>>

    suspend fun insertMyFood(id: Long, food: String)

    suspend fun deleteMyFoodById(id: Long)

    suspend fun deleteMyFoodAll()
}