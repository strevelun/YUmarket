package com.example.YUmarket.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.YUmarket.data.db.dao.BasketDao
import com.example.YUmarket.data.entity.room.BasketEntity

@Database(
    entities = [BasketEntity::class],
    version = 1,
    exportSchema = false
)
abstract class YUMarketDB : RoomDatabase() {

    abstract val basketDao: BasketDao

    companion object {
        const val DB_NAME = "YUMarketDB.db"
    }
}