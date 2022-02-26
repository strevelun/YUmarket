package com.example.myapplication23.data.repository.map

import com.example.myapplication23.data.entity.location.LocationLatLngEntity
import com.example.myapplication23.data.network.MapApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DefaultMapRepository(
    private val mapApiService: MapApiService,
    private val ioDispatcher: CoroutineDispatcher
) : MapRepository {
    override suspend fun getReverseGeoInformation(locationLatLngEntity: LocationLatLngEntity) =
        withContext(ioDispatcher) {
            val response = mapApiService.getReverseGeoCode(
                lat = locationLatLngEntity.latitude,
                lon = locationLatLngEntity.longitude
            )
            if (response.isSuccessful) {
                response.body()?.addressInfo
            } else {
                null
            }
        }
}