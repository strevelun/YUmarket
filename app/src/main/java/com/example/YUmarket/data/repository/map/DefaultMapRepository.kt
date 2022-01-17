package com.example.YUmarket.data.repository.map

import com.example.YUmarket.data.entity.location.LocationLatLngEntity
import com.example.YUmarket.data.network.MapApiService
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