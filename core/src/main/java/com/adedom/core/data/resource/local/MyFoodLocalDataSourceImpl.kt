package com.adedom.core.data.resource.local

import com.adedom.myfood.MyFoodDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import myfood.database.MyFoodDatabaseQueries
import myfood.database.MyFoodEntity

class MyFoodLocalDataSourceImpl(
    db: MyFoodDatabase,
) : MyFoodLocalDataSource {

    private val queries: MyFoodDatabaseQueries = db.myFoodDatabaseQueries

    override suspend fun getMyFoodById(id: Long): MyFoodEntity? {
        return withContext(Dispatchers.IO) {
            queries.getMyFoodById(id).executeAsOneOrNull()
        }
    }

    override fun getMyFoodAll(): Flow<List<MyFoodEntity>> {
        return queries.getMyFoodAll().asFlow().mapToList()
    }

    override suspend fun insertMyFood(id: Long, food: String) {
        return withContext(Dispatchers.IO) {
            queries.insertMyFood(id, food)
        }
    }

    override suspend fun deleteMyFoodById(id: Long) {
        return withContext(Dispatchers.IO) {
            queries.deleteMyFoodById(id)
        }
    }

    override suspend fun deleteMyFoodAll() {
        return withContext(Dispatchers.IO) {
            queries.deleteMyFoodAll()
        }
    }
}