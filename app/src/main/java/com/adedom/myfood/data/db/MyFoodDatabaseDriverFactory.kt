package com.adedom.myfood.data.db

import android.content.Context
import com.adedom.myfood.MyFoodDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

class MyFoodDatabaseDriverFactory(private val context: Context) {

    fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(MyFoodDatabase.Schema, context, "MyFoodDatabase.db")
    }
}