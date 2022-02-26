package com.example.myapplication23.util

import androidx.annotation.StringRes
import com.example.myapplication23.data.entity.location.MapSearchInfoEntity

sealed class LocationState {
    object Uninitialized : LocationState()
    object Loading : LocationState()

    data class Success(
        val mapSearchInfoEntity: MapSearchInfoEntity,
        val isLocationSame: Boolean,
//        val foodMenuListInBasket: List<RestaurantFoodEntity>? = null
    ) : LocationState()

    data class Error(
        @StringRes val errorMessage: Int
    ) : LocationState()
}
