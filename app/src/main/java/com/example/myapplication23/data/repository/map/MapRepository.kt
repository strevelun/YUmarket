package com.example.myapplication23.data.repository.map

import com.example.myapplication23.data.entity.location.LocationLatLngEntity
import com.example.myapplication23.data.response.address.AddressInfo

interface MapRepository {
    suspend fun getReverseGeoInformation(
        locationLatLngEntity: LocationLatLngEntity
    ): AddressInfo?
}