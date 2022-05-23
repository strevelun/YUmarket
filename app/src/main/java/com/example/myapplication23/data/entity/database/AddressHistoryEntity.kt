package com.example.myapplication23.data.entity.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication23.data.entity.location.MapSearchInfoEntity

@Entity
data class AddressHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val lat: Double,
    val lng: Double
)