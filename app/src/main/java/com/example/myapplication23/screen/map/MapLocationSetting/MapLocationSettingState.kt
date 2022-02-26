package com.example.myapplication23.screen.map.MapLocationSetting

import androidx.annotation.StringRes
import com.example.myapplication23.data.entity.location.MapSearchInfoEntity

sealed class MapLocationSettingState
{
        object Uninitialized : MapLocationSettingState()
        object Loading : MapLocationSettingState()

        data class Success(
            val mapSearchInfoEntity: MapSearchInfoEntity
        ) : MapLocationSettingState()

        data class Error(
            @StringRes val errorMessage: Int
        ) : MapLocationSettingState()

}
