package com.example.YUmarket.screen

import androidx.lifecycle.viewModelScope
import com.example.YUmarket.R
import com.example.YUmarket.data.entity.location.LocationLatLngEntity
import com.example.YUmarket.data.entity.location.MapSearchInfoEntity
import com.example.YUmarket.data.repository.map.MapRepository
import com.example.YUmarket.screen.base.BaseViewModel
import com.example.YUmarket.util.LocationData
import com.example.YUmarket.util.LocationState
import kotlinx.coroutines.launch

class MainViewModel(
    private val mapRepository: MapRepository
) : BaseViewModel() {

//    val mainStateLiveData = MutableLiveData<MainState>(MainState.Uninitialized)

    fun getMapSearchInfo() {
    }

    fun getReverseGeoInformation(
        locationLatLngEntity: LocationLatLngEntity
    ) = viewModelScope.launch {

        val currentLocation = locationLatLngEntity

        val addressInfo = mapRepository.getReverseGeoInformation(locationLatLngEntity)

        addressInfo?.let { addressInfo ->

            val mapSearchInfoEntityResult = MapSearchInfoEntity(
                fullAddress = addressInfo.fullAddress ?: "주소 정보 없음",
                name = addressInfo.buildingName ?: "주소 정보 없음",
                locationLatLng = currentLocation
            )

//            mainStateLiveData.value = MainState.Success(
//                mapSearchInfoEntity = mapSearchInfoEntityResult,
//                isLocationSame = false // TODO stub
//            )

            LocationData.locationStateLiveData.value = LocationState.Success(
                mapSearchInfoEntity = MapSearchInfoEntity(
                    fullAddress = addressInfo.fullAddress ?: "주소 정보 없음",
                    name = addressInfo.buildingName ?: "주소 정보 없음",
                    locationLatLng = currentLocation),
                isLocationSame = false
            )

//            LocationData.locationStateLiveData.value = LocationState.Success(mapSearchInfoEntityResult)

        } ?: kotlin.run {
//            mainStateLiveData.value = MainState.Error(
//                R.string.cannot_load_address_info
//            )

            LocationData.locationStateLiveData.value = LocationState.Error(
                R.string.cannot_load_address_info
            )
        }
    }
}