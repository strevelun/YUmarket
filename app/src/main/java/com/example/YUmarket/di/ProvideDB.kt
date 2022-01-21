package com.example.YUmarket.di

import android.content.Context
import androidx.room.Room
import com.example.YUmarket.data.db.YUMarketDB

fun provideDB(context: Context) =
    Room.databaseBuilder(context, YUMarketDB::class.java, YUMarketDB.DB_NAME).build()


fun provideBasketDao(database: YUMarketDB) =
    database.basketDao
