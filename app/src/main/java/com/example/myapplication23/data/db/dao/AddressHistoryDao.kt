package com.example.myapplication23.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication23.data.entity.database.AddressHistoryEntity

@Dao
interface AddressHistoryDao {
    @Query("SELECT * FROM AddressHistoryEntity ORDER BY id DESC")
    suspend fun getAllAddresses(): List<AddressHistoryEntity>

    @Insert
    suspend fun insertAddress(address: AddressHistoryEntity)

    @Delete
    suspend fun deleteAddress(address: AddressHistoryEntity)

    @Query("delete from AddressHistoryEntity")
    suspend fun deleteAllAddresses()
}