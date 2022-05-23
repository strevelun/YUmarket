package com.example.myapplication23.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication23.data.db.YUMarketDB

fun provideDatabase(context: Context) =
    Room.databaseBuilder(context, YUMarketDB::class.java, YUMarketDB.DB_NAME).build()

fun provideAddressHistoryDao(database: YUMarketDB) =
    database.addressHistoryDao