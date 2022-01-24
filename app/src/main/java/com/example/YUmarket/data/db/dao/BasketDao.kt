package com.example.YUmarket.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.YUmarket.data.entity.room.BasketEntity

@Dao
interface BasketDao {
    @Query("select * from BasketEntity")
    suspend fun getAllItems(): List<BasketEntity>

    @Insert
    suspend fun insertItem(basketEntity: BasketEntity)

    @Query("delete from BasketEntity")
    suspend fun deleteAllItems()
}