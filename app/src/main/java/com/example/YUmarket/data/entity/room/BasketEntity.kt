package com.example.YUmarket.data.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BasketEntity(
    @PrimaryKey val id: String,
    val itemName: String,
    val storeNameId: Long,
    val amount: Int
)
