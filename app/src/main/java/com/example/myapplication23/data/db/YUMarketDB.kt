package com.example.myapplication23.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication23.data.db.dao.AddressHistoryDao
import com.example.myapplication23.data.entity.database.AddressHistoryEntity

@Database(
    entities = [
        AddressHistoryEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class YUMarketDB : RoomDatabase() {

    abstract val addressHistoryDao: AddressHistoryDao

    companion object {
        const val DB_NAME = "YUMarketDB.db"
    }
}