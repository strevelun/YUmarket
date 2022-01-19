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

        addressInfo?.let { addressInfoResult ->

            LocationData.locationStateLiveData.value = LocationState.Success(
                mapSearchInfoEntity = MapSearchInfoEntity(
                    fullAddress = addressInfoResult.fullAddress ?: "주소 정보 없음",
                    name = addressInfoResult.buildingName ?: "주소 정보 없음",
                    locationLatLng = currentLocation),
                isLocationSame = false
            )

        } ?: kotlin.run {
            LocationData.locationStateLiveData.value = LocationState.Error(
                R.string.cannot_load_address_info
            )
        }
    }
}