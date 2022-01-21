package com.example.YUmarket.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.YUmarket.data.entity.room.BasketEntity

@Dao
interface BasketDao {
    @Query("select * from BasketEntity")
    fun getAllItems(): List<BasketEntity>

    @Insert
    fun insertItem(basketEntity: BasketEntity)

    @Query("delete from BasketEntity")
    fun deleteAllItems()
}