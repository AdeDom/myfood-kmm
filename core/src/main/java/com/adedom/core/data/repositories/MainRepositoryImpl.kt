package com.adedom.core.data.repositories

class MainRepositoryImpl : MainRepository {

    override fun getMainData(): Long {
        return System.currentTimeMillis()
    }
}