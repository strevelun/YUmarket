package com.example.myapplication23.screen.map.MapLocationSetting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication23.data.entity.location.LocationLatLngEntity
import com.example.myapplication23.data.entity.location.MapSearchInfoEntity
import com.example.myapplication23.data.repository.map.MapRepository
import com.example.myapplication23.screen.base.BaseViewModel
import kotlinx.coroutines.launch

class MapLocationSettingViewModel(private val mapRepository: MapRepository) : BaseViewModel() {

    val MapLocationSettingStateLiveData = MutableLiveData<MapLocationSettingState>(
        MapLocationSettingState.Uninitialized
    )

    fun getMapSearchInfo(): MapSearchInfoEntity? {
        when (val data = MapLocationSettingStateLiveData.value) {
            is MapLocationSettingState.Success -> {
                return data.mapSearchInfoEntity
            }
        }
        return null
    }

    fun getReverseGeoInformation(
        locationLatLngEntity: LocationLatLngEntity
    ) = viewModelScope.launch {

        val currentLocation = locationLatLngEntity

        val addressInfo = mapRepository.getReverseGeoInformation(locationLatLngEntity)

        addressInfo?.let { addressInfo ->

            MapLocationSettingStateLiveData.value = MapLocationSettingState.Success(
                MapSearchInfoEntity(
                    fullAddress = addressInfo.fullAddress ?: "주소 정보 없음",
                    name = addressInfo.buildingName ?: "주소 정보 없음",
                    locationLatLng = currentLocation
                )
            )

        } ?: kotlin.run {

        }
    }
}