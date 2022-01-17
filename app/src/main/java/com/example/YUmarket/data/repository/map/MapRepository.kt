package com.example.YUmarket.data.repository.map

import com.example.YUmarket.data.entity.location.LocationLatLngEntity
import com.example.YUmarket.data.response.address.AddressInfo

interface MapRepository {
    suspend fun getReverseGeoInformation(
        locationLatLngEntity: LocationLatLngEntity
    ): AddressInfo?
}