package com.example.YUmarket.data.repository.basket

import com.example.YUmarket.data.entity.room.BasketEntity

interface BasketRepository {
    suspend fun getAllItems(): List<BasketEntity>

    suspend fun insertItem(item: BasketEntity)

    suspend fun deleteAllItems()
}